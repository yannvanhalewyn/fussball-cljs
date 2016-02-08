(ns fussball.core
  (:require-macros [reagent.ratom :refer [reaction]])
  (:require [reagent.core :as reagent]
            [fussball.add-match-form :as amf]
            [re-frame.core :refer [register-handler
                                   dispatch
                                   register-sub
                                   subscribe]]))

(enable-console-print!)

(register-handler
 :action
 (fn [db v]
   (prn "Db action called" db v)
   db))

(defn init-db []
  {:players
   ["David" "Yann" "Stefan" "Tom" "Richard" "Raymond" "Arjan"
    "Jaap" "Stijn" "Agnes" "Ivo" "Floris"]})

(register-handler
 :initialise-db
 init-db)

(register-handler
 :add-game
 (fn [db [_ v]]
   (prn "Added: " v)
   db))

(register-sub
 :app-state
 (fn [db] (reaction @db)))

(defn app-root []
  (let [app-state (subscribe [:app-state])]
    [:div
     [:h1 "Badass Fussball Application"]
     [:button {:on-click #(dispatch [:action "lala"])} "Test"]
     [amf/add-match-button]
     [amf/add-match-form app-state]]))

(defn ^:export main []
  (dispatch [:initialise-db])
  (reagent/render-component [app-root]
                            (. js/document (getElementById "app"))))

(main)

(defn on-js-reload []
  ;; optionally touch your app-state to force rerendering depending on
  ;; your application
  ;; (swap! app-state update-in [:__figwheel_counter] inc)
)
