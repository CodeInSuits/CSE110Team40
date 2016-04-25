# -*- coding: utf-8 -*-
__author__ = 'Dan'
__email__ = 'daz040@eng.ucsd.edu'

from assignment2 import Player, State, Action

class MinimaxPlayer(Player):
    def __init__(self):
        self.cache ={}

    def move(self, state):
        """
        Calculates the best move from the given board using the minimax
        algorithm.
        :param state: State, the current state of the board.
        :return: Action, the next move
        """

        action = None
        possibleMoves = state.actions()
        if(len(possibleMoves) == 0):
            return action

"""
    1. how to pass in the opponent's move?
    2. What index do we pass in as a second parameter to ActioN(), when
    we're already at terminal state? Since terminal state has only empty
    pits
    
"""
        for move in possibleMoves:
            newBoard = result(move)
            #action = move(newBoard)
            if newBoard.is_win():
                #return Action(newBoard.player_row, something)
            else:
                #call minimax with opponent
                #continue

        raise NotImplementedError("Need to implement this method")