#!/usr//bin/env ruby
#                 
# Author:  Abbas Moghtanei
# Date  :  03/04/11        
# Program Name: regex.rb 
# Objective: This program helps users to practice regular expressions

#********************************cls()**********************************
def cls
    print "\e[2J"
    printf "\e[%d;%dH", 1, 1
end
#***********************************************************************
BOLD_RED = "\e[1m\e[31m"
BGRD_RED = "\e[1m\e[41m"
RESET_IT = "\e[0m"
cls
str,temp_str = "",""

print RESET_IT
puts "Esc-Return to exit, Ctrl-C-Return to clear buffer"
trap("SIGINT") {str,temp_str = "",""; puts}

loop do
  if str.empty?
      print "str>> "; STDOUT.flush; str = gets.chop
  else
      print "str>> "; puts str ; STDOUT.flush 
  end

  break if str == "\e"
  if str.empty? && !temp_str.empty?
     puts "str>> #{temp_str}" 
     str = temp_str
  end   
  print "pat>> "; STDOUT.flush; pat = gets.chop
  break if pat == "\e"
   
  temp_str = str
  
  re = Regexp.new(pat)
  next if str.empty?
  if pat.include?("\\s")
     puts "--->> " + str.gsub(re,"#{BGRD_RED}\\&#{RESET_IT}");puts
  else
     puts "--->> " + str.gsub(re,"#{BOLD_RED}\\&#{RESET_IT}");puts
  end
end
puts
