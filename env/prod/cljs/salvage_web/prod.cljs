(ns salvage-web.prod
  (:require [salvage-web.core :as core]))

;;ignore println statements in prod
(set! *print-fn* (fn [& _]))

(core/init!)
