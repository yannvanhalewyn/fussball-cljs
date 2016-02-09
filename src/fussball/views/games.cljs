(ns fussball.views.games
  (:require [fussball.stats :as stats]))

(defn game-data [idx game]
  [:li {:key idx}
   [:hr]
   [:span (get-in game [:team_a :player])]
   " "
   [:span (get-in game [:team_a :score])]
   [:span " - "]
   [:span (get-in game [:team_b :score])]
   " "
   [:span (get-in game [:team_b :player])]])

(defn total-goals-table [games]
  (let [goals-per-player (reverse (sort-by last (stats/goals-per-player games)))]
    (prn goals-per-player)
    [:table {:class-name "stats-table"}
     [:thead {:class-name "stats"}
      (into
       [:tr]
       (map (fn [[player goals]] [:th {:key player} player]) goals-per-player))]
     [:tbody {:class-name "body"}
      (into
       [:tr]
       (map (fn [[player goals]] [:td {:key player} goals]) goals-per-player))]]))

(defn display [games]
  (if (empty? games)
    [:i "No games"]
    [:div
     [:h3 "Goals Per Player"]
     [total-goals-table games]
     [:h3 "Games"]
     [:ul (map-indexed game-data games)]]))
