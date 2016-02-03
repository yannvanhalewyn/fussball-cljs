(ns fussball.core
  (:require [reagent.core :as reagent :refer [atom]]))

(enable-console-print!)

(println "Edits to this text should show up in your developer console.")

;; define your app data so that it doesn't get over-written on reload

(defonce app-state (atom {
  :players ["David" "Yann" "Stefan" "Tom"]
  }))

(defn select-option [option]
  [:option {:value option} option])

(defn select-with-options [name options]
  [:select {:name name}
    (map select-option options)])

(defn add-match-button []
  [:input {:type "button" :value "+" :on-click #(js/alert "Foobar!")} ])

(defn input-with-label [placeholder]
  [:input {:type "text" :placeholder placeholder}])

(defn add-match-form [players]
  [:div {:class-name "add-match-form"}
    [:div {:class-name "team-a"}
      [select-with-options "Player 1" players]
      [input-with-label "Score"]]
    [:div {:class-name "team-b"}
      [select-with-options "Player 2" players]
      [input-with-label "Score"]]
    [:input {:type "submit" :value "Add" :onClick #(js/alert "ok")}]])

(defn app-root []
  [:div
    [:h1 "Badass Fussball Application"]
    [add-match-button]
    [add-match-form (:players @app-state)]])

(reagent/render-component [app-root]
                          (. js/document (getElementById "app")))


(defn on-js-reload []
  ;; optionally touch your app-state to force rerendering depending on
  ;; your application
  ;; (swap! app-state update-in [:__figwheel_counter] inc)
)
