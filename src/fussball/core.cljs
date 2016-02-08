(ns fussball.core
  (:require [reagent.core :as reagent]
            [fussball.actions]
            [fussball.add-match-form :as amf]
            [re-frame.core :refer [dispatch
                                   subscribe]]))

(enable-console-print!)

(defn app-root [app-state]
  [:div
   [:h1 "Badass Fussball Application"]
   [amf/add-match-button]
   [amf/add-match-form app-state]])

(defn ^:export main []
  (dispatch [:initialise-db])
  (let [app-state (subscribe [:app-state])]
    (reagent/render-component [app-root app-state]
                              (. js/document (getElementById "app")))))

(main)

(defn on-js-reload []
  ;; optionally touch your app-state to force rerendering depending on
  ;; your application
  ;; (swap! app-state update-in [:__figwheel_counter] inc)
)
