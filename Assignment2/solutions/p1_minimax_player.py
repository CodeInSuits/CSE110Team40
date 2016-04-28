# -*- coding: utf-8 -*-
__author__ = 'Utkrisht Rajkumar, Lingyi Kong'
__email__ = 'urajkuma@ucsd.edu, l1kong@ucsd.edu'

import sys

sys.path.append("../src")
from assignment2 import Player, State, Action

class MinimaxPlayer(Player):
    def __init__(self):
        self.cache ={}
    
    def Min(self, state):
        """ 
        Args: Takes in state
        returns: utility for OUR player
        """

        #if terminal state, then return utility of that state
        if(state.is_terminal()):
            # utility function returns value for opponent
            # thus we take the opposite value for our player
            return state.utility(self)

        #else, generate new states
        possibleMoves = state.actions()

        #if no possible moves for Min, then call Max again on that same state
        if(len(possibleMoves) == 0):
            # need to switch opponent when calling Max again
            # result function with None action simply returns
            # the same state with opponent
            return self.Max(state.result(None))

        # Our opponent wants to have -1 for our player's utility
        currentScore = 1 # assuming losing position for our opponent
        bestAction = possibleMoves[0]

        for nextMove in possibleMoves:
            newBoard = state.result(nextMove)
            newScore = self.Max(newBoard)
            if(currentScore > newScore):
                currentScore = newScore
        return currentScore


    def Max(self, state):
        """ Args: Takes in state
        returns: Utility for our player
        """
        if(state.is_terminal()):
            return state.utility(self)

        possibleMoves = state.actions()

        #if no possible moves for Max, then call Min again on that same state
        if(len(possibleMoves) == 0):
            # need to switch opponent when calling Max again
            # result function with None action simply returns
            # the same state with opponent
            return self.Min(state.result(None))

        currentScore = -1 # Initialize losing position
        bestAction = possibleMoves[0] # If no better option, take first one

        for nextMove in possibleMoves:
            newBoard = state.result(nextMove)
            newScore = self.Min(newBoard)
            if(currentScore < newScore):
                currentScore = newScore

                
        return currentScore

    def move(self, state):
        """3
        Calculates the best move from the given board using the minimax
        algorithm.
        :param state: State, the current state of the board.
        :return: best Action for current plyer
        """
        action = None
        possibleMoves = state.actions()
        if(len(possibleMoves) == 0):
            return action
        bestUtil = -1
        bestAction = possibleMoves[0] # default action
        # running minimax algorithm to find best move
        # return action with max min_utility
        for nextMove in possibleMoves:
            newBoard = state.result(nextMove)
            newScore = self.Min(newBoard) # We are maximizing the min here
            if bestUtil < newScore:
                bestUtil = newScore
                bestAction = nextMove
                
        return bestAction

        raise NotImplementedError("Need to implement this method")
