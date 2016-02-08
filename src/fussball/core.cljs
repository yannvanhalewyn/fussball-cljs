(ns fussball.core
  (:require [reagent.core :as reagent]
            [fussball.actions]
            [fussball.views.games :as games]
            [fussball.add-match-form :as amf]
            [re-frame.core :refer [dispatch subscribe]]))

(enable-console-print!)

(defn fussball []
  (let [app-state (subscribe [:app-state])]
    (fn []
      [:div
       [:button {:on-click #(dispatch [:print-state])} "Print State"]
       [:h1 "Badass Fussball Application"]
       [amf/add-match-form
        (sort (:players @app-state))
        (:add-match-form @app-state)]
       [games/list (:games @app-state)]])))

(defn ^:export run []
  (dispatch [:initialise-db])
  (reagent/render-component [fussball]
                            (. js/document (getElementById "app"))))

(defn on-js-reload []
  (run))
