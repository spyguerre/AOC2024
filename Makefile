# Variables
OS := $(shell uname -s 2>/dev/null || echo Windows) # Detect OS
JAVAC = javac
JAVA = java

# Directories
SRC_DIR = src
BIN_DIR = bin

# Main class (without .java or .class)
MAIN_CLASS = Main

# Find all .java files
SOURCES = $(wildcard $(SRC_DIR)/*.java)

# Replace .java with .class for all source files
CLASSES = $(patsubst $(SRC_DIR)/%.java, $(BIN_DIR)/%.class, $(SOURCES))

# Path separator adjustment for Windows
ifeq ($(OS),Windows)
    MKDIR = mkdir
    RM = rmdir /S /Q
    PATH_SEP = ;
else
    MKDIR = mkdir -p
    RM = rm -rf
    PATH_SEP = :
endif

# Default target: build everything
all: $(BIN_DIR) $(CLASSES)

# Rule to create bin directory
$(BIN_DIR):
	$(MKDIR) $(BIN_DIR)

# Rule to compile .java files
$(BIN_DIR)/%.class: $(SRC_DIR)/%.java
	$(JAVAC) -d $(BIN_DIR) $<

# Run the main class
run: all
	$(JAVA) -cp $(BIN_DIR)$(PATH_SEP) $(MAIN_CLASS)

# Clean the build
clean:
	$(RM) $(BIN_DIR)
