# Variables
JAVAC = javac
JAVA = java
SRC_DIR = src
BIN_DIR = bin
MAIN_CLASS = Main

# Find all .java files in the source directory
SOURCES = $(wildcard $(SRC_DIR)/*.java)

# Replace .java with .class for all source files
CLASSES = $(patsubst $(SRC_DIR)/%.java, $(BIN_DIR)/%.class, $(SOURCES))

# Default target
all: $(BIN_DIR) $(CLASSES)

# Rule to create bin directory
$(BIN_DIR):
	mkdir -p $(BIN_DIR)

# Rule to compile .java files to .class files
$(BIN_DIR)/%.class: $(SRC_DIR)/%.java
	$(JAVAC) -d $(BIN_DIR) $<

# Run the main class
run: all
	$(JAVA) -cp $(BIN_DIR) $(MAIN_CLASS)

# Clean the build
clean:
	rm -rf $(BIN_DIR)
