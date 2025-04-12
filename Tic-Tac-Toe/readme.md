# Reqiurements:
1. initially it will be for 3x3 board, but it should be extensible to nxn board.
2. The game should provide options for setting up the players with name
3. Each player should be able to pick their coin.
4. Coins can be of different shapes and colors.

### Gameplay:
1. Players take turns to place their marks (X or O) on an empty cell of the grid. 
The game should enforce alternating turns between players
2. Each player should be able to click or select the cell they want to place their mark in.

### Winning Conditions
1. The game should detect when a player has won by placing n of their marks in a row, column, or diagonal. 
2. If a player achieves a winning condition, the game should display a message indicating the winner 
3. The game should prevent further moves once a player has won.

### Draw Condition:
If all cells are filled and no player has won, the game should recognise a draw or tie condition and display a message indicating the draw.

### Restart/Reset:
1. Players should have the option to restart the game after a win, draw, or at any point during the game.
2. The game board should be cleared, and the players should have the option to start a new game.

### Scorekeeping:
The game may keep track of the number of wins for each player across multiple rounds.