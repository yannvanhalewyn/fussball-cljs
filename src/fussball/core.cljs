(ns fussball.core
  (:require [reagent.core :as reagent]
            [fussball.flux.dispatcher :refer [dispatch]]
            [fussball.actions]
            [fussball.store :as store]
            [fussball.views.games :as games]
            [fussball.add-match-form :as amf]))

(enable-console-print!)

(defn fussball []
  (let [app-state (store/get-state)]
    (fn []
      [:div
       [:button {:on-click #(dispatch :print-state)} "Print State"]
       [:h1 "Badass Fussball Application"]
       [amf/add-match-form
        (sort (:players @app-state))
        (:add-match-form @app-state)]
       [games/display (:games @app-state)]])))

(defn ^:export run []
  (reagent/render-component [fussball]
                            (. js/document (getElementById "app"))))

(defn on-js-reload []
  (run))
