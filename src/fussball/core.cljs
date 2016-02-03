(ns fussball.core
  (:require [reagent.core :as reagent :refer [atom]]
            [fussball.add-match-form :as amf]))

(enable-console-print!)

(println "Edits to this text should show up in your developer console.")

;; define your app data so that it doesn't get over-written on reload

(defonce app-state (atom {
  :players ["David" "Yann" "Stefan" "Tom" "Richard" "Raymond" "Arjan" "Jaap" "Stijn" "Agnes" "Ivo" "Floris"]
  }))


(defn app-root []
  [:div
    [:h1 "Badass Fussball Application"]
    [amf/add-match-button]
    [amf/add-match-form (sort (:players @app-state))]])

(reagent/render-component [app-root]
                          (. js/document (getElementById "app")))


(defn on-js-reload []
  ;; optionally touch your app-state to force rerendering depending on
  ;; your application
  ;; (swap! app-state update-in [:__figwheel_counter] inc)
)
