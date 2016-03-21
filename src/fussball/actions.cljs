(ns fussball.actions
  (:require-macros [fussball.flux.core :refer [defaction]]))

(defaction :score-input [team score]
  {:payload_type :set_score_input
   :team team
   :score score})


(defaction :player-selected [team player]
  {:payload_type :set_selected_player
   :team team
   :player player})

(defaction :add-game [game]
  {:payload_type :add_game
   :game game})

(defaction :print-state []
  {:payload_type :print_state})
