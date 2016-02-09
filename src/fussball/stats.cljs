(ns fussball.stats)

(defn goals-per-player [games]
  (->> games
      (map (fn [g] [(:team_a g) (:team_b g)]))
      flatten
      (reduce (fn [out game]
                (update out (:player game) + (:score game))) {})))
