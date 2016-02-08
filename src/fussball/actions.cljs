(ns fussball.actions
  (:require-macros [reagent.ratom :refer [reaction]])
  (:require [re-frame.core :refer [register-handler register-sub]]))

(register-handler
 :initialise-db
 (fn []
   {:players
    ["David" "Yann" "Stefan" "Tom" "Richard" "Raymond" "Arjan"
     "Jaap" "Stijn" "Agnes" "Ivo" "Floris"]}))

(register-handler
 :add-game
 (fn [db [_ v]]
   (prn "Added: " v)
   db))

(register-sub
 :app-state
 (fn [db] (reaction @db)))
