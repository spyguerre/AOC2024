# Directory paths
SRC_DIR = src
BIN_DIR = bin

# Default day is set to Day01
DAY = Day01
MAIN_CLASS = $(DAY).Main

# Compiler and flags
JAVAC = javac
JAVA = java
JFLAGS = -d $(BIN_DIR)

# Platform detection for cross-platform support
ifeq ($(OS),Windows_NT)
	FIND = for /R $(SRC_DIR) %%f in (*.java) do @echo %%f
    RM = del /f /q
    RMDIR = rmdir /s /q
    SLASH = \\
else
	FIND = find $(SRC_DIR) -name "*.java"
    RM = rm -f
    RMDIR = rm -rf
    SLASH = /
endif

# Default target: compile and run the code
run: $(JAVA_FILES)
	$(JAVAC) $(JFLAGS) $(shell $(FIND))
	$(JAVA) -cp $(BIN_DIR) $(MAIN_CLASS)

# Clean up generated class files
clean:
	$(RMDIR) $(BIN_DIR)
