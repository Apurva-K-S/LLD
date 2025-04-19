## Rules
1. 2 player game.
2. each player will choose a color - white or black.
3. white chosen player will always start first. then each player will move turn by turn
4. each player will have 16 pieces.
5. pieces: kind, queen, 2 bishop, 2 knights, 2 rooks, 8 pawns.
6. game will end if black wins or white wins or stalemate.

## What is implemented?
1. Basic case of black win or white win.
2. complete implementation of code - code is working.
3. Sample testcase

  Turn | Player | Move Description | From | To

   1 | White | Move pawn to open queen | (6,3) | (4,3)

   2 | Black | Move pawn to open path | (1,4) | (3,4)
   
   3 | White | Move queen forward | (7,3) | (3,7)
   
   4 | Black | Move pawn (doesn't help defense) | (1,0) | (2,0)

   5 | White | Queen captures Black king — game over | (3,7) | (0,4)


## To implement:
1. Factory Design pattern for "piece" creation.
2. Strategy Design pattern for "move" which will have canMove logic
3. Event triggering on each move.
4. Some moves for pieces like: King - castling.
5. Some end game status : STALEMATE. (added sample code in chatgpt_code.txt)
6. Adding validation for moves (added sample code in chatgpt_code.txt)