(ns fussball.core
  (:require [reagent.core :as reagent :refer [atom]]))

(enable-console-print!)

(println "Edits to this text should show up in your developer console.")

;; define your app data so that it doesn't get over-written on reload

(defonce app-state (atom {:text "Fussball"}))

(defn add-match-button []
  [:input {:type "button" :value "+" :on-click #(js/alert "Foobar!")} ])

(defn input-with-label [placeholder]
  [:input {:type "text" :placeholder placeholder}])

(defn add-match-form []
  [:div {:class-name "add-match-form"}
    [:div {:class-name "team-a"}
      [input-with-label "Player 1"]
      [input-with-label "Player 2"]
      [input-with-label "Score"]]
    [:div {:class-name "team-b"}
      [input-with-label "Player 1"]
      [input-with-label "Player 2"]
      [input-with-label "Score"]]
    [:input {:type "submit" :value "Add" :onClick #(js/alert "ok")}]])

(defn app-root []
  [:div
    [:h1 (:text @app-state)]
    [add-match-button]
    [add-match-form]])

(reagent/render-component [app-root]
                          (. js/document (getElementById "app")))


(defn on-js-reload []
  ;; optionally touch your app-state to force rerendering depending on
  ;; your application
  ;; (swap! app-state update-in [:__figwheel_counter] inc)
)
