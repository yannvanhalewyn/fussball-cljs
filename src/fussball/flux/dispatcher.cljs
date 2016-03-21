(ns fussball.flux.dispatcher
  (:require [fussball.store :as store]))

(defmulti action (fn [action_type params] action_type))

(defmethod action :default [action_type params] (js/alert (str "No action registered for " action_type)))

(defn dispatch [action_type & params]
  (let [payload (action action_type params)]
    (store/execute payload)))
