(ns fussball.views.games)

(defn game-data [game]
  [:li {:key (:id game)}
   [:hr]
   [:span (get-in game [:team_a :player])]
   " "
   [:span (get-in game [:team_a :score])]
   [:span " - "]
   [:span (get-in game [:team_b :score])]
   " "
   [:span (get-in game [:team_b :player])]])

(defn list [games]
  [:ul
   (map game-data games)])
