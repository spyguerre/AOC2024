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

# Platform detection for cross-platform support
ifeq ($(OS),Windows_NT)
    RM = del /f /q
    RMDIR = rmdir /s /q
    SLASH = \\
else
    RM = rm -f
    RMDIR = rm -rf
    SLASH = /
endif

# Default target: compile and run the code
run: $(JAVA_FILES)
	$(JAVAC) $(JFLAGS) $(JAVA_FILES)
	$(JAVA) -cp $(BIN_DIR) $(DAY).Main

# Clean up generated class files
clean:
	$(RM) $(BIN_DIR)$(SLASH)*.class
	$(RMDIR) $(BIN_DIR)
