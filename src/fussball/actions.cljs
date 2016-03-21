(ns fussball.actions
  (:require [fussball.dispatcher :refer [action]]))

(defmethod action
  :score-input
  [_ [team score]]
  {:payload_type :set_score_input
   :team team
   :score score})

(defmethod action
  :player-selected
  [_ [team player]]
  {:payload_type :set_selected_player
   :team team
   :player player})

(defmethod action
  :add-game
  [_ [game]]
  {:payload_type :add_game
   :game game})

(defmethod action
  :print-state
  []
  {:payload_type :print_state})
