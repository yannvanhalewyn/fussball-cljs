(ns fussball.core
  (:require [reagent.core :as reagent]
            [fussball.actions]
            [fussball.add-match-form :as amf]
            [re-frame.core :refer [dispatch
                                   subscribe]]))

(enable-console-print!)

(defn app-root [app-state]
  (let [app-state (subscribe [:app-state])]
    (fn []
      [:div
       [:button {:on-click #(dispatch [:print-state])} "Print State"]
       [:h1 "Badass Fussball Application"]
       [amf/add-match-form
        (sort (:players @app-state))
        (:add-match-form @app-state)]
       [:p (:games app-state)]])))

(defn ^:export run []
  (dispatch [:initialise-db])
  (reagent/render-component [app-root]
                            (. js/document (getElementById "app"))))

(defn on-js-reload []
  ;; optionally touch your app-state to force rerendering depending on
  ;; your application
  ;; (swap! app-state update-in [:__figwheel_counter] inc)
)
