(ns fussball.add-match-form
  (:require [fussball.dispatcher :refer [dispatch]]))

;; Form elements
(defn select-option [option & {disabled :disabled}]
  [:option {:disabled disabled :key option :value option} option])

(defn player-selector [team players taken selected]
  [:select
   {:value selected
    :on-change #(dispatch :player-selected team (.-target.value %))}
   (map #(select-option % :disabled (= taken %)) players)])

(defn score-input [{:keys [on-change value]}]
  [:input {:type "number" :value value :on-change on-change}])

(defn team-input [& {:keys [team players already-picked fields]}]
  [:div
   [player-selector team players already-picked (:player fields)]
   [score-input 
    {:value (:score fields)
     :on-change
     #(dispatch :score-input team (js/parseInt (.-target.value %)))}]])

(defn add-match-form [players form-data]
  [:form {:on-submit (fn [e] (.preventDefault e)(dispatch :add-game form-data))}
   [team-input
    :team :team_a
    :players players
    :already-picked (get-in form-data [:team_b :player])
    :fields (:team_a form-data)]
   [team-input
    :team :team_b
    :players players
    :already-picked (get-in form-data [:team_a :player])
    :fields (:team_b form-data)]
   [:input {:type "submit" :value "Add"}]])
