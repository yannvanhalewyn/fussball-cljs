(ns fussball.add-match-form
  (:require [reagent.core :refer [atom]]
            [re-frame.core :refer [dispatch]]))

;; Form elements
(defn select-option [option]
  [:option {:key option :value option} option])

(defn player-selector [team players selected]
  [:select
   {:value selected
    :on-change #(dispatch [:player-selected team (.-target.value %)])}
   (map select-option players)])

(defn score-input [{:keys [on-change value]}]
  [:input {:type "number" :value value :on-change on-change}])

(defn team-input [team players fields]
  [:div
   [player-selector team players (:player fields)]
   [score-input 
    {:value (:score fields)
     :on-change
     #(dispatch [:score-input team (js/parseInt (.-target.value %))])}]])

(defn add-match-form [players form-data]
  [:div
   (team-input :team_a players (:team_a form-data))
   (team-input :team_b players (:team_b form-data))
   [:input {:type "submit" :value "Add" :on-click #(dispatch [:add-game])}]])
