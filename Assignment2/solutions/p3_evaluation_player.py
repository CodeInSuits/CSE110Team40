# -*- coding: utf-8 -*-
__author__ = 'Lingyi Kong'
__email__ = 'l1kong@ucsd.edu'

from assignment2 import Player, State, Action


class EvaluationPlayer(Player):
    def move(self, state):
        """Calculates the best move after 1-step look-ahead with a simple
        evaluation function.
        :param state: State, the current state of the board.
        :return: Action, the next move
        """

        # *You do not need to modify this method.*
        best_value = -1.0

        actions = state.actions()
        if not actions:
            actions = [None]

        best_move = actions[0]
        for action in actions:
            result_state = state.result(action)
            value = self.evaluate(result_state, state.player_row)
            if value > best_value:
                best_value = value
                best_move = action

        # Return the move with the highest evaluation value
        return best_move

    def evaluate(self, state, my_row):
        """
        Evaluates the state for the player with the given row
        """
        player_goal_stones = state.board[state.player_goal_idx]
        opponent_goal_stones = state.board[state.opponent_goal_idx]
        player_pit_stones=0
        for i in xrange(*state.possible_action_range()):
            player_pit_stones += state.board[i]
        total = 2*state.M*state.N # both sides
        opponent_pit_stones= total - player_goal_stones-opponent_goal_stones-player_pit_stones
        prefactor = 1.0/2.0/state.M/state.N
        # We use minus sign because this is actually opponent's state
        weight = -(player_goal_stones-opponent_goal_stones+player_pit_stones-opponent_pit_stones)
        return prefactor * weight

    
        raise NotImplementedError("Need to implement this method")
