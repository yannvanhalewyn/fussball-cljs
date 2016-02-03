(ns fussball.add-match-form)

(defn select-option [option]
  [:option {:value option} option])

(defn select-with-options [name options]
  [:select {:name name}
    (map select-option options)])

(defn add-match-button []
  [:input {:type "button" :value "+" :on-click #(js/alert "Foobar!")} ])

(defn input-with-label [name {:keys [on-change]}]
  [:input {:type "text" :placeholder name :on-change on-change}])

(defn update-input [atom param value]
  (js/alert "ok")
  (swap! atom assoc param value))

(defn add-match-form [players]
  (let [scores (atom {:team_a 0 :team_b 0})]
    [:div {:class-name "add-match-form"}
      [:div {:class-name "team-a"}
        [select-with-options "Player 1" players]
        [input-with-label "Score" {:on-change (fn [e] (swap! scores assoc :team_a (.-target.value e)))}]]
      [:div {:class-name "team-b"}
        [select-with-options "Player 2" players]
        [input-with-label "Score" {:on-change (fn [e] (swap! scores assoc :team_b (.-target.value e)))}]]
      [:input {:type "submit" :value "Add" :on-click #(js/alert @scores)}]]))
