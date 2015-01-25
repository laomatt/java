#!/usr/local/bin/ruby -w
#                 
# Author:  Abbas Moghtanei
# Date  :  10/03/09        
# modified 12/12/09 (to enable us sending our file(s) to the specific
# directory.
# last modified date: 1/31/10 (display error if ftp fails).
# Program Name: rcopy.rb 
# This program basically sends and receives files to/from a remote host.
# It also helps administration of .hupfile such as delete, create, modify.

require 'net/ftp'
require 'getoptlong'

if RUBY_VERSION.to_f < 1.9
   class String
      #*******************************ord()**********************************
      def ord()
          return( self[0] )
      end
      #****************************start_with?()*****************************
      def start_with?(str)
          return(false) if str.empty?
          return( self[0,str.size] == str )
      end
      #****************************end_with?()*******************************
      def end_with?(str)
          return(false) if str.empty?
          return( self[-str.size, str.size] == str )
      end
      #****************************each_char()*******************************
      def each_char
          self.each_byte {|b| yield b.chr}
      end
   end
end
#******************************printable()*******************************
def printable()
p=<<'EOS'
0123456789abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ!"#$%&'()*+,-./:;<=>?@[\]^_`{|}~ 	

EOS
return p
end
#********************************agemo()*********************************
# encrypts str
def agemo(str)
   s = ""
   str.each_char do |x|
      c=0
      printable.each_char do |i|
          if x == i
             k = sprintf("%d%02d", rand(10), c)
             s += k
          end
          c += 1
      end
   end
   return s
end
#********************************omega()*********************************
# decrypts str
def omega(str)
   s = ""
   str.chomp!
   if (str.size) % 3 != 0
      puts"Invalid code"
      return nil
   end
   if RUBY_VERSION.to_f < 1.9
      1.step(str.size, 3) do |x|
         i = str[x,2].to_i
         s +=  (printable()[i]).chr
      end
   else
      1.step(str.size, 3) do |x|
         i = str[x,2].to_i
         s +=  printable()[i]
      end
   end
   return s
end
#*********************************mkhup()********************************
def mkhup(aflag=false)

   path=ENV['HOME'].chomp + "/.hupfile"
   mode = aflag ? "a" : "w"

   File.open(path, mode) do |f|
      loop do 
         print"Hostname:"; host = gets.chomp
         print"Username:"; user = gets.chomp
         print"Password:"; pass = gets.chomp
         f.puts agemo(host)
         f.puts agemo(user)
         f.puts agemo(pass)
         STDOUT.print"Continue (y/n)?:"
         ans = gets.chomp
         break if ans =~ /^[nN]/ 
      end
   end
end
#*********************************gethup()*******************************
def gethup()
   path=ENV['HOME'].chomp + "/.hupfile"

   abort(path+" does not exists\n") if !File.exists?(path)
   abort("can not open#{path}\n") if (fin = File.open(path, "r")) == nil

   host, user, pass = [], [], []

   while(!fin.eof())
      host << omega(fin.gets).chomp
      user << omega(fin.gets).chomp
      pass << omega(fin.gets).chomp
   end
   fin.close()
   return host,user,pass
end
#*********************************help()*********************************
def help()
   mesg = <<-EOJ
       
       usage: #{$0[2,$0.size]} <option(s)> path1 path2 ...
       options are:

       -h  or --help    :  displays this help message
       -m  or --hupfile :  creates a .hupfile
       -a  or --append  :  appends new hups (host,user,pass) to .hupfile
       -q  or --quiet   :  quiet mode
       -r  or --receive :  brings files from the remote host
       -s  or --send    :  send files to the remote host
       -l  or --list    :  list all remote files
       -l  or --list "regex":  list those files match with the regex
       -L  or --longlist:  list all remote files (long format)
       -L  or --longlist "regex":  list those files match with the regex
                (long format) 
       -i  or --include host1,host2,... : includes transfering files
           from/to specific host(s)
       -e  or --exclude host1,host2,... : excludes transfering files
           from/to specific host(s)
       -v  or --version :  displays the version number

       -S  or --show    :  shows the contents of .hupfile
       -H  or --hostnames: shows all hostnames in .hupfile
       -d  or --delete host1,host2,... deletes host1, host2 info
           from .hupfile
       -D  or --debug   : turns on debugging mode
       -f  or --folder specifies the destination folder (or directory)
   EOJ
   puts mesg
   exit 0
end
#************************************************************************

sflag,rflag,mflag,hflag,lflag,iflag = false,false,false,false,false,false
qflag,eflag,vflag,wflag,xflag,aflag = false,false,false,false,false,false
gflag,dflag,fflag = false,false,false

ver = 1.91 # version number

help if ARGV.size == 0

=begin
sflag = true if ARGV.delete("-s") == "-s"
rflag = true if ARGV.delete("-r") == "-r"
mflag = true if ARGV.delete("-m") == "-m"
hflag = true if ARGV.delete("-h") == "-h"
lflag = true if ARGV.delete("-l") == "-l"
debug = false
=end

opts = GetoptLong.new(
                       ["-l", "--list",     GetoptLong::NO_ARGUMENT],
                       ["-L", "--longlist", GetoptLong::NO_ARGUMENT],
                       ["-h", "--help",     GetoptLong::NO_ARGUMENT],
                       ["-m", "--hupfile",  GetoptLong::NO_ARGUMENT],
                       ["-a", "--append",   GetoptLong::NO_ARGUMENT],
                       ["-H", "--hostnames",GetoptLong::NO_ARGUMENT],
                       ["-q", "--quiet",    GetoptLong::NO_ARGUMENT],
                       ["-r", "--receive",  GetoptLong::NO_ARGUMENT],
                       ["-s", "--send",     GetoptLong::NO_ARGUMENT],
                       ["-S", "--show",     GetoptLong::NO_ARGUMENT],
                       ["-v", "--version",  GetoptLong::NO_ARGUMENT],
                       ["-D", "--debug",    GetoptLong::NO_ARGUMENT],
                       ["-i", "--include",  GetoptLong::REQUIRED_ARGUMENT],
                       ["-d", "--delete",   GetoptLong::REQUIRED_ARGUMENT],
                       ["-f", "--folder",   GetoptLong::REQUIRED_ARGUMENT],
                       ["-e", "--exclude",  GetoptLong::REQUIRED_ARGUMENT]
                     )

language = nil
fcount = 0
$hostname = ""
$dir_name = "."

begin
   opts.each do |opt, arg|
      case opt
         when "-e" 
            eflag = true
            $hostname = arg
         when "-h" 
            hflag = true
         when "-q" 
            qflag = true
         when "-r" 
            rflag = true
         when "-s" 
            sflag = true
         when "-S" 
            wflag = true
         when "-m" 
            mflag = true
         when "-H" 
            gflag = true
         when "-a" 
            aflag = true
         when "-v" 
            vflag = true
         when "-l" 
            lflag = true
         when "-L" 
            xflag = true
         when "-i" 
            iflag = true
            $hostname = arg
         when "-d" 
            dflag = true
            $hostname = arg
         when "-D" 
            debug = true
         when "-f" 
            fflag = true
            $dir_name = arg
      end
   end
rescue => err
    #puts err
    exit(1)
end

help() if hflag

if vflag
   puts "version: #{ver}" 
   exit(0) 
end

if mflag || aflag
   mkhup(aflag)
   if mflag
      puts "Created a new .hupfile"
   else
      puts "Modified .hupfile"
   end
   exit 0
end

abort("Error: can not use -i and -e to gether") if iflag && eflag
sflag = true if (rflag || sflag) == false # -s is default

abort("Invalid command\n") if (sflag && rflag) == true

hostarr = $hostname.split(',') if !$hostname.empty?

h,u,p = gethup()
newarr = []

#begin
if dflag    #delete records from .hupfile
   h.each_with_index  do |host1,i|
      found = false
      hostarr.each do |host2|
        found = true if host2 == host1 
      end
      if not found
         newarr << agemo(h[i])
         newarr << agemo(u[i])
         newarr << agemo(p[i])
      end 
   end

   fpath=ENV['HOME'].chomp + "/.hupfile"

   File.open(fpath, "w") do |f|
      newarr.each {|line| f.puts line}
   end
   exit 0
end
#end

h.size.times do |i|
  host,user,pass = h[i], u[i], p[i]
  if gflag
     puts host
     next
  end 
  if wflag
     puts
     puts "host: #{h[i]}"
     puts "user: #{u[i]}"
     puts "pass: #{p[i]}"
     next
  end
  if iflag || eflag
     flag = false
     hostarr.each do |myh|
        if host == myh 
           flag = true if iflag
           flag = false if eflag
           break
        end
     end 
     next if flag == false
  end
  begin
      ftp = Net::FTP.open(host, user, pass) 
      ftp.passive = true 
      # ftp.debug_mode = true if debug  # causes some problems!!
  rescue
      abort("Error: ftp failed\n")
  end
  ARGV[0] = "./." if ARGV.size == 0 && lflag 
  ARGV[0] = "./." if ARGV.size == 0 && xflag 
  ARGV.each do |path| 
     begin
         ftp.chdir(File.dirname(path)) if path.include?('/') 
     rescue
         STDERR.puts "not such a directory #{File.dirname(path)}"
         exit 1
     end
     if lflag || xflag
        puts "=============(#{host})============"
        arr = lflag ? ftp.nlst() : ftp.list()
        regex = File.basename(path)
#       puts regex; exit 0
        if lflag
           arr.each {|f| puts f if f =~ /#{regex}/ }
        else
           arr.each do |f|
              lsarr = f.split(/\s/)
              lsarr = lsarr.delete_if { |x| x == "" }
           #  puts lsarr
              puts f if lsarr[8] =~ /#{regex}/ 
           end
        end
     elsif rflag
        begin
           #STDOUT.puts "before rflag, file=#{File.basename(path)}"
           ftp.getbinaryfile(File.basename(path)) 
           STDOUT.puts "#{path} successfully received from #{host}" if !qflag
        rescue
           STDERR.puts"#{File.basename(path)} does not exist"
           next
        end
     elsif sflag
        if !File.exists?(path)
           STDERR.puts "#{path} does not exist" 
        else
           # the following line allow us to send our file to the
           # specified directory. (added 12/12/09)
           ftp.chdir($dir_name) if fflag
           ftp.putbinaryfile(File.basename(path))
           STDOUT.puts "#{path} successfully transferred to #{host}" if !qflag
        end
     end
  end
ftp.close
end

exit(0)

