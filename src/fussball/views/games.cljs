(ns fussball.views.games)

(defn game-data [idx game]
  (prn idx)
  [:li {:key idx}
   [:hr]
   [:b idx " "]
   [:span (get-in game [:team_a :player])]
   " "
   [:span (get-in game [:team_a :score])]
   [:span " - "]
   [:span (get-in game [:team_b :score])]
   " "
   [:span (get-in game [:team_b :player])]])

(defn display [games]
  [:ul
   (map-indexed game-data games)])
