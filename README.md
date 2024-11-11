# ConnectFour

ConnectFour is an interactive game designed for two players who take turns to compete in a classic Connect Four challenge. This project was developed as part of a course at SUPSI (University of Applied Sciences and Arts of Southern Switzerland). The application supports saving and loading games, customizing game settings, and advanced features like undo and redo for moves.

## Key Features

- **Start Game**: Allows users to start a new game or reset to the initial state at any time.
- **Save and Load**: Games can be saved and loaded in JSON format, preserving the progress.
- **Change Name and Symbol**: Players can change their name, symbol, and color while ensuring no conflicts with the other player's settings.
- **Undo and Redo**: Players can undo or redo moves, adding flexibility during gameplay.
- **Keyboard Shortcuts**: For quicker navigation, players can use shortcuts to:
  - Select the column for the next move
  - Open a saved game file
  - Save the current game
  - Start a new game
  - Close the current game
- **InfoBar**: A constant information bar provides real-time feedback on actions and game events.

### Feature Checklist

- [x] Start a new game
- [x] Save the current game
- [x] Load a previously saved game
- [x] Change player names, symbols, and colors
- [x] Undo last move
- [x] Redo last undone move
- [x] Select columns using keyboard shortcuts
- [x] Shortcuts for all actions
- [x] Open and save games via keyboard shortcuts
- [x] Display feedback to players through InfoBar

## Project Architecture

The project follows a multilayer architecture, separating the frontend from the backend. Various design patterns were implemented to ensure modularity and flexibility:

- **Backend**: Built with a multilayer structure, clearly separating responsibilities and efficiently managing data and game logic.
- **Frontend**: Implemented using the MVC (Model-View-Controller) pattern, facilitating effective UI management and interaction.

### Design Patterns Used

- **Memento**: To save and restore game states, supporting undo/redo functionality.
- **Mediator**: To simplify communication between objects without tight coupling.
- **Observer**: To update views when the model’s state changes.
- **Command**: To implement undo and redo for moves.
- **Strategy**: To manage different strategies for saving and loading games.

## Requirements

- **JDK**: Version 21 or higher.
- **Operating System**: Compatible with Windows, macOS, and Linux.

## Usage Guide

1. **Start the Application**: Launch `ConnectFour.jar` to open the editor.
2. **New Game**: Select "Game" -> "New Game" from the menu to start a new challenge.
3. **Select Column**: Choose the column for your move via the menu or with keyboard shortcuts.
4. **Save Game**: Use "File" -> "Save Game" to save the current game.
5. **Load Game**: Go to "File" -> "Load Game" to resume a saved game.
6. **Edit Player Settings**: Modify player settings via "Settings" -> "Edit Player".
7. **Undo or Redo Move**: Access "Undo" and "Redo" options in the "Edit" menu or use shortcuts.
8. **Close Game**: Select "Game" -> "Close" to end the current game.
## Clone

```sh
git clone https://github.com/DominikPanzarella/connect_four.git
```

## Install

Install the backend library

```sh
cd backed && mvn clean install
```

Create the jar file

```sh
cd frontend && mvn clean package
```

## Usage

```sh
cd frontend && java -jar target/connectfour-jar-with-dependencies.jar
```

## Contribute

For contributions, bug reports, or suggestions, feel free to open an issue on GitHub.

## Authors

👤 **Dominik Panzarella**
[![Instagram](https://img.shields.io/badge/Instagram-%23E4405F.svg?logo=Instagram&logoColor=white)](https://www.instagram.com/__dom_/) [![LinkedIn](https://img.shields.io/badge/LinkedIn-%230077B5.svg?logo=linkedin&logoColor=white)](https://www.linkedin.com/in/dominik-panzarella-a8412817a) [![Stack Overflow](https://img.shields.io/badge/-Stackoverflow-FE7A16?logo=stack-overflow&logoColor=white)](https://stackoverflow.com/users/21978407/dominik-panzarella) [![YouTube](https://img.shields.io/badge/YouTube-%23FF0000.svg?logo=YouTube&logoColor=white)](https://www.youtube.com/channel/UC128UoG-qfNOf6TCjarx5Mw) 

## Contact

For more information, you can contact the developers via email.

---

> This project is intended for educational purposes only and not for commercial use.


