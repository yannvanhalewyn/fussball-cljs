(ns fussball.add-match-form)

(defn select-option [option]
  [:option {:value option} option])

(defn select-with-options [name options]
  [:select {:name name}
    (map select-option options)])

(defn add-match-button []
  [:input {:type "button" :value "+" :on-click #(js/alert "Foobar!")} ])

(defn input-with-label [name {:keys [on-change value]}]
  [:input {:type "text" :placeholder name :on-change on-change}])

(defn set-score [atom param value]
  (swap! atom assoc param value))

(defn team-input [name {:keys [on-change players]}]
  [:div
        [select-with-options "Player 1" players]
        [input-with-label "Score" {:on-change on-change}]])

(defn add-match-form [players]
  (let [scores (atom {:team_a 0 :team_b 0})]
    [:div
      (team-input "Team A" {:players players :on-change (fn [e] (swap! scores assoc :team_a (.-target.value e)))})
      (team-input "Team B" {:players players :on-change (fn [e] (swap! scores assoc :team_b (.-target.value e)))})
      [:input {:type "submit" :value "Add" :on-click #(js/alert @scores)}]]))
