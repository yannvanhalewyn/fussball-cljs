(ns fussball.dispatcher
  (:require [fussball.store :as store]))

(defmulti action (fn [action_type & params] action_type))

(defn dispatch [action_type & params]
  (let [payload (action action_type params)]
    (store/execute payload)))
