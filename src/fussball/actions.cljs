(ns fussball.actions
  (:require-macros [reagent.ratom :refer [reaction]])
  (:require [re-frame.core :refer [register-handler register-sub]]))

;; Handlers
(register-handler
 :initialise-db
 (fn []
   {:players
    ["David" "Yann" "Stefan" "Tom" "Richard" "Raymond" "Arjan"
     "Jaap" "Stijn" "Agnes" "Ivo" "Floris"]
    :add-match-form
    {:team_a
     {:player "David"
      :score 0}
     :team_b
     {:player "Yann"
      :score 0}}}))

(register-handler
 :score-input
 (fn [db [_ team score]]
   (assoc-in db [:add-match-form team :score] score)))

(register-handler
 :player-selected
 (fn [db [_ team player]]
   (assoc-in db [:add-match-form team :player] player)))

(register-handler
 :add-game
 (fn [db [_ game]]
   (assoc db :games (conj (:games db) game))))

(register-handler
 :print-state
 (fn [db]
   (prn db)
   db))

;; Subscriptions
(register-sub
 :app-state
 (fn [db] (reaction @db)))
