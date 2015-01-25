#!/usr/local/bin/ruby
#                 
# Author:  Abbas Moghtanei
# Date  :  11/03/12        
# Program Name: mkaccessors.rb 
# Objective: This program reads the java source code, and creates getter and setter
#            mothods for all of the private and protected instance variables. 
#            Warning: The original source code will be modified by this program,
#            therefore using the -b option is recommended to back up the original
#            java source code.
# Notes:     Those decalarative lines (including instance variables) must be
#            sandwiched between //{{    and   //}}   for example:
#            //{{
#                private int x,y,x;
#                protected String s,t,w;
#            //}}
#            The simple parser in this program is not acting like a java compiler.
#            Therefore, if the program has syntax (or compiler errors), this program
#            may produce an unexpected result. So it is advised to compile the jav
#            program before using this program, to make sure the java code is 
#            error free.


require "getoptlong"
require "tempfile"
#require "fileUtils"

class String
#*******************************vacuume()********************************
    def vacuume
        self.gsub(/\s/,'')
    end
#*******************************vacuume()********************************
    def vacuume!
        self.gsub!(/\s/,'')
    end

#*******************************deep_cp()********************************
    def deep_cp
       Marshal.load(Marshal.dump(self))
    end
end
#*********************************help()*********************************
def help()
   str = <<-EOF
   mkaccessors <options> java_source_code
   Options are:
   
   -a  : creates getters and setters
   -b  : makes the backup of the file 
   -h  : displays this help
   -H  : puts the method separator to separate getters and setters
   -g  : creates getters
   -s  : creates setters

   EOF
   puts str
   exit 0
end

#*******************************process()********************************
def process(xline)
    line = xline.deep_cp
    line.strip!
    line.chop! if line =~ /;/
    abort("empty line") if line =~ /^\s$/ || line.nil?
    ax = line.split(/\s+/)
    abort("syntax error") if ax.size < 3
    security = ax.shift
    datatype = ax.shift
    line     = ax.join('')
    #puts("in process security=#{security} datatype=#{datatype}")    
    #puts("in process line=#{line}")    
    line.vacuume!
    abort("empty line") if line =~ /^\s$/ || line.nil?
    ar = line.split(',')
    abort("invalid declaration") if ar.size < 1
    return unless security == 'private' || security == 'protected'

    ar.each do |t|
       process_getter(t,datatype) if $gflag 
       process_setter(t,datatype) if $sflag 
    end
end   
#*********************************cp()***********************************
def cp(file1, file2)
    File.foreach(file1) do |line|
        File.open(file2, "w"){|f| f.puts(line)}
    end
end
#****************************process_getter()****************************
def process_getter(var, datatype)
    fname = "get"+var.capitalize()
    #puts("now in process_getter")
    $tempfile << mkheader(fname) + "\n"  if $zflag
    $tempfile << "public #{datatype} #{fname}" + "()\n"  
    $tempfile << "{\n"
    $tempfile << "    return(#{var});\n"
    $tempfile << "}\n"
end

#****************************process_setter()****************************
def process_setter(var, datatype)
    fname = "set"+var.capitalize()

    #puts("now in process_setter")
    $tempfile << mkheader(fname) + "\n" if $zflag
    $tempfile << "public void #{fname}(#{datatype} #{var})\n"
    $tempfile << "{\n"
    $tempfile << "     this.#{var} = #{var};\n"
    $tempfile << "}\n"
end

#*******************************mkheader()*******************************
def mkheader(function_name)
    line = function_name + '()'
    line = '//' + line.center(70, '*')
end
#************************************************************************
opts = GetoptLong.new(
                       ["-a", "--all",      GetoptLong::NO_ARGUMENT],
                       ["-b", "--backup",   GetoptLong::NO_ARGUMENT],
                       ["-h", "--help",     GetoptLong::NO_ARGUMENT],
                       ["-H", "--header",   GetoptLong::NO_ARGUMENT],
                       ["-g", "--getter",   GetoptLong::NO_ARGUMENT],
                       ["-s", "--setter",   GetoptLong::NO_ARGUMENT]
                       
                     )

bflag,hflag,$gflag,$sflag,$zflag = false,false,false,false,false
flag = false

begin
   opts.each do |opt, arg|
      case opt
         when "-a" 
            $sflag = $gflag = true
         when "-b" 
            bflag = true
         when "-h" 
            hflag = true
         when "-H" 
            $zflag = true
         when "-g" 
            $gflag = true
         when "-s" 
            $sflag = true
      end
   end
rescue => err
    #puts err
    exit(1)
end

help if hflag or ARGV.size == 0

if ARGV.size > 0
   infile = ARGV.shift()
else
   abort "usage: #{myself} <options> filename"
end


abort "#{infile} does not exist\n" if !File.exists?(infile)

   
$tempfile = Tempfile.new("tempfile.java");


if bflag
   backup_file = infile + ".bak" 
   #FileUtils.cp(infile, backup_file) # does not work on hills
   cp(infile, backup_file)
   
end

buffer = File.readlines(infile)

buffer.each do |line|
   line.chomp!

   if line =~ /^\s*\/\/\{\{/ 
      flag = true
      next
   end
   if line =~ /^\s*\/\/\}\}/
      flag = false
      next
   end
   process(line) if flag
end

$tempfile.close() 

# remove the last right brace '}' from the buffer

buffer.size().downto(0) do |i|
   if buffer[i] =~ /^\s*\}\s*$/
      buffer[i].gsub!(/^\s*\}\s*$/, '')
      break
   end
end

# append the contents of the tempfile to the buffer

File.foreach($tempfile.path) {|line| buffer << ("   " + line)}

buffer << "}"  # put the last right brace '}' to the buffer 

#buffer.each{|line| puts line}  # for test

# write back the entire buffer to the file

File.open(infile, "w") do |out|
   buffer.each {|line| out.puts(line)}
end

