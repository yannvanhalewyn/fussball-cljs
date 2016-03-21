(ns fussball.store
  (:require [reagent.core :as r]))

(def initial-state
  {:players
    ["David" "Yann" "Stefan" "Tom" "Richard" "Raymond" "Arjan"
     "Jaap" "Stijn" "Agnes" "Ivo" "Floris"]
    :add-match-form
    {:team_a
     {:player "David"
      :score 0}
     :team_b
     {:player "Yann"
      :score 0}}})

(defonce app-state (r/atom initial-state))

(defn get-state []
  app-state)

(defn print-state [db]
  (prn db)
  db)

(defn default-handler [db payload]
  (prn (str "No such handler " (:payload_type payload)))
  db)

(defn handle-payload [db payload]
  (case (:payload_type payload)
    :set_score_input (assoc-in db [:add-match-form (:team payload) :score] (:score payload))
    :set_selected_player (assoc-in db [:add-match-form (:team payload) :player] (:player payload))
    :print_state (print-state db)
    :add_game (update db :games conj (:game payload))
    (default-handler db payload)))

(defn execute [payload]
  (prn payload)
  (swap! app-state handle-payload payload))
