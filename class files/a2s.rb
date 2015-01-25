#!/usr/local/bin/ruby
# Author: Abbas Moghtanei
# Date  : 2/22/08
# This program converts applets to stand-alone programs. It also has 
# options to compile and run the converted stand-alone program.


require "getoptlong"

###################################die()##################################
def die(mesg)
  puts("Error-- #{mesg}")
  exit(1)
end
####################################usage()###############################
def usage()
   puts("Usage:")
   puts("a2s.rb <option> filename.java")
   puts("where options are:")
   puts("-c or --compile: compile the stand-alone java program")
   puts("-h or --help:    display this help")
   puts("-r or --run:     run the stand-alone java program (needs -c too)")
   puts("-v or --verbose: verbose")
   puts("filename.java:   name of the applet file")
end
##########################remove_last_right_brace()#######################
def remove_last_right_brace(fname)
   f = File.open(fname, "r+")
   line = f.gets().chomp()
   if line == "//---a2s.rb---"   # it is a stand-alone program. Do nothing
      f.close()
      die("This is already a stand-alone program\n")
      exit(0)
   end
   fsize = File.size(fname)

   p = fsize - 1  # skip over EOF
   while p
     f.seek(p)
     c = f.getc.chr
     if c == '}'
        f.seek(p)
        f.putc("\n")
        break
     end
     p -= 1
   end
   f.close()
end
#########################################################################
if ARGV.size == 0 then
  usage()
  exit(1)
end

opts = GetoptLong.new(
                      ["-c", "--compile", GetoptLong::NO_ARGUMENT],
                      ["-h", "--help",    GetoptLong::NO_ARGUMENT],
                      ["-r", "--run",     GetoptLong::NO_ARGUMENT],
                      ["-v", "--verbose", GetoptLong::NO_ARGUMENT]
                     )

hflag, cflag, rflag, vflag =  false, false, false, false

begin
   opts.each do |opt, arg|
      case opt
         when "-h" 
            hflag = true
         when "-c" 
            cflag = true
         when "-r" 
            rflag = true
         when "-v" 
            vflag = true
      end
   end
rescue => err
    puts err
end
if ARGV.size > 0
   filename = ARGV.shift()
else
   usage()
   exit(1)
end

if hflag
   usage()
   exit(0)
end

die("Not a java program") if filename[-5, 5] != ".java" 
die("Invalid filename") if filename.size == 5
die("#{filename} does not exists") if not File.exists?(filename) 
puts("converting the applet to the stand-alone program...") if vflag
classname=filename[0, filename.size - 5]
puts("removing \'}\' from the end of the applet program...") if vflag
remove_last_right_brace(filename)

puts("adding the main method to the end of the applet program...") if vflag
f = File.open(filename, 'a');

str = <<EOJ
    public static void main(String args[])
    {
       JFrame jf = new JFrame(\"#{classname}\");
       #{classname} CN = new #{classname}();
       CN.init();
       jf.setSize(900,700);
       Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
       int screenWidth = screenSize.width;
       int screenHeight= screenSize.height;
       Dimension frameSize = jf.getSize();
       int x = (screenWidth - frameSize.width) / 2;
       int y = (screenHeight- frameSize.height)/ 2;
       jf.setLocation(x, y);
       jf.add(CN);
       jf.setVisible(true);
       jf.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }
}
       
EOJ

f.close()

t = File.open("import_javax_swing_dot_star", "w")
t.puts("//---a2s.rb---") 
t.puts("import javax.swing.*;")

f = File.open(filename)
while(line = f.gets())
    t.puts(line)
end
t.puts(str)

t.close()
f.close()

File.rename("import_javax_swing_dot_star", filename)
if cflag
   print "Compiling #{filename}....\n"
   system("javac #{filename}")
end
if rflag
   die("you need to use the -c option too") if not cflag
   print "running #{classname}...\n"
   system("java #{classname}")
end
   
 

exit(0)

