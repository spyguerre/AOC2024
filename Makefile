# Directory paths
SRC_DIR = src
BIN_DIR = bin

# Default day is set to Day01
DAY = Day01

# Compiler and flags
JAVAC = javac
JAVA = java
JFLAGS = -d $(BIN_DIR)

# Java files
JAVA_FILES = $(wildcard $(SRC_DIR)/$(DAY)/*.java $(SRC_DIR)/utils/*.java)

# Default target: compile and run the code
run: $(JAVA_FILES) $(PAIR_FILE)
	$(JAVAC) $(JFLAGS) $(JAVA_FILES) $(PAIR_FILE)
	$(JAVA) -cp $(BIN_DIR) $(DAY).Main

# Clean up generated class files
clean:
	rm -rf $(BIN_DIR)/*