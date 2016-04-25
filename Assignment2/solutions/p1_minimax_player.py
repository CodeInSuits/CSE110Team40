# -*- coding: utf-8 -*-
__author__ = 'Dan'
__email__ = 'daz040@eng.ucsd.edu'

from assignment2 import Player, State, Action

class MinimaxPlayer(Player):
    def __init__(self):
        self.cache ={}

    def move(self, state):
        """3
        Calculates the best move from the given board using the minimax
        algorithm.
        :param state: State, the current state of the board.
        :return: Action, the next move
        """

        action = None
        possibleMoves = state.actions()
        if(len(possibleMoves) == 0):
            return action
        bestUtil = -1
        bestAction = 0
        #get the inital actions and states from given state
        for i, move in enumerate(possibleMoves):
            newBoard = state.result(move)
            """
                Based on which player's turn it is, call MAX or MIN.
                This will iterate through the tree and run the minimax
                algorithm. Then, when the MAX or MIN return all the way
                back to the move function, check if the util of that 
                state is a win or lose. Do that for all the initial states
                and keep track which action will result in a win. Then call
                Action on that action.
            """
            if(state.player == 0):
                currentUtil = Max(newBoard)
            else:
                currentUtil = Min(newBoard)

            #update which of the initial actions give the best result
            if(bestUtil < currentUtil):
                bestUtil = currentUtil
                bestAction = i

        action = Action(state.player_row, i)


"""
    Naming convention
"""
        raise NotImplementedError("Need to implement this method")

    
    def Min(state):
        """ 
        Args: Takes in state
        returns: -1,0,1 based on utility of the player of that state 
        """

        #if terminal state, then return utility of that state
        if(state.is_terminal):
            return utility(state.player)

        #else, generate new states
        possibleMoves = state.actions()

        #if no possible moves for Min, then call Max again on that same state
        if(len(possibleMoves) == 0):
            return Max(state)

        currentScore = 0 #to keep track of which is the winner at each level

        for (move in possibleMoves):
            newBoard = state.result(move)
            newScore = Max(newBoard)
            if(newScore >= currentScore):
                currentScore = newScore

        return currentScore


    def Max(state):
        """ Args: Takes in state
        returns: -1,0,1 based on utility of the player of that state """
        if(state.is_terminal)
            return utility(state.player)

        possibleMoves = state.actions()

        #if no possible moves for Max, then call Min again on that same state
        if(len(possibleMoves) == 0):
            return Min(state)

        currentScore = 0 #to keep track of which is winner at each level

        for (move in possibleMoves):
            newBoard = state.result(move)
            newScore = Min(newBoard)
            if(currentScore < newScore):
                currentScore = newScore

        return currentScore
