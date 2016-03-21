(ns fussball.flux.core)

(defmacro defaction [name args body]
  (list 'defmethod 'fussball.flux.dispatcher/action name ['_ args] body))
