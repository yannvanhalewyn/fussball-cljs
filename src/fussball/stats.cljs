(ns fussball.stats)

(defn goals-per-player [games]
  (->> games
      (mapcat vals)
      (reduce #(update %1 (:player %2) + (:score %2)) {})))
