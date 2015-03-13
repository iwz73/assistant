rawDataPath <- "/home/hsiehpinghan/git/assistant/r-assistant/src/test/resources/r_script/rAssistantTest.data"
table <- read.csv(rawDataPath)
print(table)
write.csv(table, file="/tmp/test.result")