(ns fussball.store
  (:require [reagent.core :as r]))

;; Setups
;; ======
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

;; Handlers
;; ========
(defmulti handler (fn [db payload] (:payload_type payload)))

(defmethod handler :set_score_input [db payload]
  (assoc-in db [:add-match-form (:team payload) :score] (:score payload)))

(defmethod handler :set_selected_player [db payload]
  (assoc-in db [:add-match-form (:team payload) :player] (:player payload)))

(defmethod handler :add_game [db payload]
  (update db :games conj (:game payload)))

(defmethod handler :print_state [db _]
  (prn db)
  db)

(defmethod handler :default [db payload]
  (prn (str "No such handler " (:payload_type payload)))
  db)

;; Public
;; ======
(defn execute [payload]
  (swap! app-state handler payload))

(defn get-state []
  app-state)
