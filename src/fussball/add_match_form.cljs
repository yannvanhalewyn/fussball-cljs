(ns fussball.add-match-form
  (:require [reagent.core :refer [atom]]
            [re-frame.core :refer [dispatch]]))

(defn select-option [option]
  [:option {:value option} option])

(defn select-with-options [name options]
  [:select {:name name}
    (map select-option options)])

(defn add-match-button []
  [:input {:type "button" :value "+" :on-click #(js/alert "Foobar!")} ])

(defn input-with-label [name {:keys [on-change value]}]
  [:input {:type "number" :placeholder name :on-change on-change}])

(defn set-score [scores team value]
  (swap! scores assoc team (js/parseInt value)))

(defn team-input [name {:keys [on-change players]}]
  [:div
        [select-with-options "Player 1" players]
        [input-with-label "Score" {:on-change on-change}]])

(defn add-match-form [app-state]
  (let [scores (atom {:team_a 0 :team_b 0})]
    [:div
      (team-input "Team A" {:players (sort (:players @app-state)) :on-change #(set-score scores :team_a (.-target.value %))})
      (team-input "Team B" {:players (sort (:players @app-state)) :on-change #(set-score scores :team_b (.-target.value %))})
      [:input {:type "submit" :value "Add" :on-click #(dispatch [:add-game @scores])}]]))
