select log.customer_id, ROW_NUMBER() OVER () as number_of_viewed_contents, hcondition.*
FROM hotlead_content content
         INNER JOIN hotlead_condition AS hcondition
                    ON hcondition.condition_id = content.condition_id
         inner JOIN (select log.*
                     from access_log as log
                     where log.customer_id = '1169231266') as log
                    ON log.action = content.action_name and
                       CASE
                           WHEN content.url is not null and (content.second_parameter_name is not null) then
                               log.page_url = content.url
                                   and case content.second_parameter_name
                                           when 'セミナーID' then
                                               case  content.second_parameter_judgment
                                                   when '完全一致' then checkAllMatch(content.second_parameter_value, log.seminar_id)
                                                   when '前方一致' then checkStartMatch(content.second_parameter_value, log.seminar_id)
                                                   when '中間一致' then checkMiddleMatch(content.second_parameter_value, log.seminar_id)
                                                   when '後方一致' then checkSuffixMatch(content.second_parameter_value, log.seminar_id)
                                                   END
                                           when 'xmail' then
                                               case  content.second_parameter_judgment
                                                   when '完全一致' then checkAllMatch(content.second_parameter_value, log.x_mail)
                                                   when '前方一致' then checkStartMatch(content.second_parameter_value, log.x_mail)
                                                   when '中間一致' then checkMiddleMatch(content.second_parameter_value, log.x_mail)
                                                   when '後方一致' then checkSuffixMatch(content.second_parameter_value, log.x_mail)
                                                   END
                                           when 'セグメントID' then
                                               case  content.second_parameter_judgment
                                                   when '完全一致' then checkAllMatch(content.second_parameter_value, log.segment_id)
                                                   when '前方一致' then checkStartMatch(content.second_parameter_value, log.segment_id)
                                                   when '中間一致' then checkMiddleMatch(content.second_parameter_value, log.segment_id)
                                                   when '後方一致' then checkSuffixMatch(content.second_parameter_value, log.segment_id)
                                                   END
                                           when '動画情報' then
                                               case  content.second_parameter_judgment
                                                   when '完全一致' then checkAllMatch(content.second_parameter_value, log.video_information)
                                                   when '前方一致' then checkStartMatch(content.second_parameter_value, log.video_information)
                                                   when '中間一致' then checkMiddleMatch(content.second_parameter_value, log.video_information)
                                                   when '後方一致' then checkSuffixMatch(content.second_parameter_value, log.video_information)
                                                   END
                                           when 'ファイル名' then
                                               case  content.second_parameter_judgment
                                                   when '完全一致' then checkAllMatch(content.second_parameter_value, log.file_name)
                                                   when '前方一致' then checkStartMatch(content.second_parameter_value, log.file_name)
                                                   when '中間一致' then checkMiddleMatch(content.second_parameter_value, log.file_name)
                                                   when '後方一致' then checkSuffixMatch(content.second_parameter_value, log.file_name)
                                                   END
                                           when '文書情報' then
                                               case  content.second_parameter_judgment
                                                   when '完全一致' then checkAllMatch(content.second_parameter_value, log.document_information)
                                                   when '前方一致' then checkStartMatch(content.second_parameter_value, log.document_information)
                                                   when '中間一致' then checkMiddleMatch(content.second_parameter_value, log.document_information)
                                                   when '後方一致' then checkSuffixMatch(content.second_parameter_value, log.document_information)
                                                   END
                                           when 'ログ受付日時' then
                                               case  content.second_parameter_judgment
                                                   when '完全一致' then checkAllMatch(content.second_parameter_value, log.date_time)
                                                   when '前方一致' then checkStartMatch(content.second_parameter_value, log.date_time)
                                                   when '中間一致' then checkMiddleMatch(content.second_parameter_value, log.date_time)
                                                   when '後方一致' then checkSuffixMatch(content.second_parameter_value, log.date_time)
                                                   END
                                           when 'リモートIPアドレス' then
                                               case  content.second_parameter_judgment
                                                   when '完全一致' then checkAllMatch(content.second_parameter_value, log.ip_address)
                                                   when '前方一致' then checkStartMatch(content.second_parameter_value, log.ip_address)
                                                   when '中間一致' then checkMiddleMatch(content.second_parameter_value, log.ip_address)
                                                   when '後方一致' then checkSuffixMatch(content.second_parameter_value, log.ip_address)
                                                   END
                                           when 'モード' then
                                               case  content.second_parameter_judgment
                                                   when '完全一致' then checkAllMatch(content.second_parameter_value, log.mode)
                                                   when '前方一致' then checkStartMatch(content.second_parameter_value, log.mode)
                                                   when '中間一致' then checkMiddleMatch(content.second_parameter_value, log.mode)
                                                   when '後方一致' then checkSuffixMatch(content.second_parameter_value, log.mode)
                                                   END
                                           when 'デバイス' then
                                               case  content.second_parameter_judgment
                                                   when '完全一致' then checkAllMatch(content.second_parameter_value, log.device)
                                                   when '前方一致' then checkStartMatch(content.second_parameter_value, log.device)
                                                   when '中間一致' then checkMiddleMatch(content.second_parameter_value, log.device)
                                                   when '後方一致' then checkSuffixMatch(content.second_parameter_value, log.device)
                                                   END
                                           when 'セッションID' then
                                               case  content.second_parameter_judgment
                                                   when '完全一致' then checkAllMatch(content.second_parameter_value, log.session_id)
                                                   when '前方一致' then checkStartMatch(content.second_parameter_value, log.session_id)
                                                   when '中間一致' then checkMiddleMatch(content.second_parameter_value, log.session_id)
                                                   when '後方一致' then checkSuffixMatch(content.second_parameter_value, log.session_id)
                                                   END
                                           when 'セッション状況' then
                                               case  content.second_parameter_judgment
                                                   when '完全一致' then checkAllMatch(content.second_parameter_value, log.session_status)
                                                   when '前方一致' then checkStartMatch(content.second_parameter_value, log.session_status)
                                                   when '中間一致' then checkMiddleMatch(content.second_parameter_value, log.session_status)
                                                   when '後方一致' then checkSuffixMatch(content.second_parameter_value, log.session_status)
                                                   END
                                           when '顧客ID' then
                                               case  content.second_parameter_judgment
                                                   when '完全一致' then checkAllMatch(content.second_parameter_value, log.customer_id)
                                                   when '前方一致' then checkStartMatch(content.second_parameter_value, log.customer_id)
                                                   when '中間一致' then checkMiddleMatch(content.second_parameter_value, log.customer_id)
                                                   when '後方一致' then checkSuffixMatch(content.second_parameter_value, log.customer_id)
                                                   END
                                           when 'ランディングフラグ' then
                                               case  content.second_parameter_judgment
                                                   when '完全一致' then checkAllMatch(content.second_parameter_value, log.landing_flag)
                                                   when '前方一致' then checkStartMatch(content.second_parameter_value, log.landing_flag)
                                                   when '中間一致' then checkMiddleMatch(content.second_parameter_value, log.landing_flag)
                                                   when '後方一致' then checkSuffixMatch(content.second_parameter_value, log.landing_flag)
                                                   END
                                           when 'URL/ダウンロードファイル' then
                                               case  content.second_parameter_judgment
                                                   when '完全一致' then checkAllMatch(content.second_parameter_value, log.page_url)
                                                   when '前方一致' then checkStartMatch(content.second_parameter_value, log.page_url)
                                                   when '中間一致' then checkMiddleMatch(content.second_parameter_value, log.page_url)
                                                   when '後方一致' then checkSuffixMatch(content.second_parameter_value, log.page_url)
                                                   END
                                           when 'ページ名' then
                                               case  content.second_parameter_judgment
                                                   when '完全一致' then checkAllMatch(content.second_parameter_value, log.page_name)
                                                   when '前方一致' then checkStartMatch(content.second_parameter_value, log.page_name)
                                                   when '中間一致' then checkMiddleMatch(content.second_parameter_value, log.page_name)
                                                   when '後方一致' then checkSuffixMatch(content.second_parameter_value, log.page_name)
                                                   END
                                           when 'アクション' then
                                               case  content.second_parameter_judgment
                                                   when '完全一致' then checkAllMatch(content.second_parameter_value, log.action)
                                                   when '前方一致' then checkStartMatch(content.second_parameter_value, log.action)
                                                   when '中間一致' then checkMiddleMatch(content.second_parameter_value, log.action)
                                                   when '後方一致' then checkSuffixMatch(content.second_parameter_value, log.action)
                                                   END
                                           when '外部サイトリファラー' then
                                               case  content.second_parameter_judgment
                                                   when '完全一致' then checkAllMatch(content.second_parameter_value, log.external_site_referrer)
                                                   when '前方一致' then checkStartMatch(content.second_parameter_value, log.external_site_referrer)
                                                   when '中間一致' then checkMiddleMatch(content.second_parameter_value, log.external_site_referrer)
                                                   when '後方一致' then checkSuffixMatch(content.second_parameter_value, log.external_site_referrer)
                                                   END
                                           when '参照元ドメイン名' then
                                               case  content.second_parameter_judgment
                                                   when '完全一致' then checkAllMatch(content.second_parameter_value, log.referring_domain_name)
                                                   when '前方一致' then checkStartMatch(content.second_parameter_value, log.referring_domain_name)
                                                   when '中間一致' then checkMiddleMatch(content.second_parameter_value, log.referring_domain_name)
                                                   when '後方一致' then checkSuffixMatch(content.second_parameter_value, log.referring_domain_name)
                                                   END
                                           when '流入元種別' then
                                               case  content.second_parameter_judgment
                                                   when '完全一致' then checkAllMatch(content.second_parameter_value, log.inflow_source_type)
                                                   when '前方一致' then checkStartMatch(content.second_parameter_value, log.inflow_source_type)
                                                   when '中間一致' then checkMiddleMatch(content.second_parameter_value, log.inflow_source_type)
                                                   when '後方一致' then checkSuffixMatch(content.second_parameter_value, log.inflow_source_type)
                                                   END
                                           when '検索エンジン名' then
                                               case  content.second_parameter_judgment
                                                   when '完全一致' then checkAllMatch(content.second_parameter_value, log.search_engine_name)
                                                   when '前方一致' then checkStartMatch(content.second_parameter_value, log.search_engine_name)
                                                   when '中間一致' then checkMiddleMatch(content.second_parameter_value, log.search_engine_name)
                                                   when '後方一致' then checkSuffixMatch(content.second_parameter_value, log.search_engine_name)
                                                   END
                                           when '参照元サイト種別' then
                                               case  content.second_parameter_judgment
                                                   when '完全一致' then checkAllMatch(content.second_parameter_value, log.referrer_site_type)
                                                   when '前方一致' then checkStartMatch(content.second_parameter_value, log.referrer_site_type)
                                                   when '中間一致' then checkMiddleMatch(content.second_parameter_value, log.referrer_site_type)
                                                   when '後方一致' then checkSuffixMatch(content.second_parameter_value, log.referrer_site_type)
                                                   END
                                           when '参照元サイト名' then
                                               case  content.second_parameter_judgment
                                                   when '完全一致' then checkAllMatch(content.second_parameter_value, log.referrer_site_name)
                                                   when '前方一致' then checkStartMatch(content.second_parameter_value, log.referrer_site_name)
                                                   when '中間一致' then checkMiddleMatch(content.second_parameter_value, log.referrer_site_name)
                                                   when '後方一致' then checkSuffixMatch(content.second_parameter_value, log.referrer_site_name)
                                                   END
                                           when 'サイト内リファラー' then
                                               case  content.second_parameter_judgment
                                                   when '完全一致' then checkAllMatch(content.second_parameter_value, log.in_site_referrer)
                                                   when '前方一致' then checkStartMatch(content.second_parameter_value, log.in_site_referrer)
                                                   when '中間一致' then checkMiddleMatch(content.second_parameter_value, log.in_site_referrer)
                                                   when '後方一致' then checkSuffixMatch(content.second_parameter_value, log.in_site_referrer)
                                                   END
                                           when 'User-Agent' then
                                               case  content.second_parameter_judgment
                                                   when '完全一致' then checkAllMatch(content.second_parameter_value, log.user_agent)
                                                   when '前方一致' then checkStartMatch(content.second_parameter_value, log.user_agent)
                                                   when '中間一致' then checkMiddleMatch(content.second_parameter_value, log.user_agent)
                                                   when '後方一致' then checkSuffixMatch(content.second_parameter_value, log.user_agent)
                                                   END
                                           when 'OS' then
                                               case  content.second_parameter_judgment
                                                   when '完全一致' then checkAllMatch(content.second_parameter_value, log.os_name)
                                                   when '前方一致' then checkStartMatch(content.second_parameter_value, log.os_name)
                                                   when '中間一致' then checkMiddleMatch(content.second_parameter_value, log.os_name)
                                                   when '後方一致' then checkSuffixMatch(content.second_parameter_value, log.os_name)
                                                   END
                                           when 'ブラウザ' then
                                               case  content.second_parameter_judgment
                                                   when '完全一致' then checkAllMatch(content.second_parameter_value, log.browser)
                                                   when '前方一致' then checkStartMatch(content.second_parameter_value, log.browser)
                                                   when '中間一致' then checkMiddleMatch(content.second_parameter_value, log.browser)
                                                   when '後方一致' then checkSuffixMatch(content.second_parameter_value, log.browser)
                                                   END
                                           when '端末名' then
                                               case  content.second_parameter_judgment
                                                   when '完全一致' then checkAllMatch(content.second_parameter_value, log.terminal_name)
                                                   when '前方一致' then checkStartMatch(content.second_parameter_value, log.terminal_name)
                                                   when '中間一致' then checkMiddleMatch(content.second_parameter_value, log.terminal_name)
                                                   when '後方一致' then checkSuffixMatch(content.second_parameter_value, log.terminal_name)
                                                   END
                                           when 'メーカー名' then
                                               case  content.second_parameter_judgment
                                                   when '完全一致' then checkAllMatch(content.second_parameter_value, log.manufacture_name)
                                                   when '前方一致' then checkStartMatch(content.second_parameter_value, log.manufacture_name)
                                                   when '中間一致' then checkMiddleMatch(content.second_parameter_value, log.manufacture_name)
                                                   when '後方一致' then checkSuffixMatch(content.second_parameter_value, log.manufacture_name)
                                                   END
                                           when '国コード' then
                                               case  content.second_parameter_judgment
                                                   when '完全一致' then checkAllMatch(content.second_parameter_value, log.country_code)
                                                   when '前方一致' then checkStartMatch(content.second_parameter_value, log.country_code)
                                                   when '中間一致' then checkMiddleMatch(content.second_parameter_value, log.country_code)
                                                   when '後方一致' then checkSuffixMatch(content.second_parameter_value, log.country_code)
                                                   END
                                           when '地域名' then
                                               case  content.second_parameter_judgment
                                                   when '完全一致' then checkAllMatch(content.second_parameter_value, log.region_name)
                                                   when '前方一致' then checkStartMatch(content.second_parameter_value, log.region_name)
                                                   when '中間一致' then checkMiddleMatch(content.second_parameter_value, log.region_name)
                                                   when '後方一致' then checkSuffixMatch(content.second_parameter_value, log.region_name)
                                                   END
                                           when '都市名' then
                                               case  content.second_parameter_judgment
                                                   when '完全一致' then checkAllMatch(content.second_parameter_value, log.name_of_city)
                                                   when '前方一致' then checkStartMatch(content.second_parameter_value, log.name_of_city)
                                                   when '中間一致' then checkMiddleMatch(content.second_parameter_value, log.name_of_city)
                                                   when '後方一致' then checkSuffixMatch(content.second_parameter_value, log.name_of_city)
                                                   END
                                           when 'リンク識別子' then
                                               case  content.second_parameter_judgment
                                                   when '完全一致' then checkAllMatch(content.second_parameter_value, log.link_identifier)
                                                   when '前方一致' then checkStartMatch(content.second_parameter_value, log.link_identifier)
                                                   when '中間一致' then checkMiddleMatch(content.second_parameter_value, log.link_identifier)
                                                   when '後方一致' then checkSuffixMatch(content.second_parameter_value, log.link_identifier)
                                                   END
                                           when 'utm_source' then
                                               case  content.second_parameter_judgment
                                                   when '完全一致' then checkAllMatch(content.second_parameter_value, log.utm_source)
                                                   when '前方一致' then checkStartMatch(content.second_parameter_value, log.utm_source)
                                                   when '中間一致' then checkMiddleMatch(content.second_parameter_value, log.utm_source)
                                                   when '後方一致' then checkSuffixMatch(content.second_parameter_value, log.utm_source)
                                                   END
                                           when 'utm_medium' then
                                               case  content.second_parameter_judgment
                                                   when '完全一致' then checkAllMatch(content.second_parameter_value, log.utm_medium)
                                                   when '前方一致' then checkStartMatch(content.second_parameter_value, log.utm_medium)
                                                   when '中間一致' then checkMiddleMatch(content.second_parameter_value, log.utm_medium)
                                                   when '後方一致' then checkSuffixMatch(content.second_parameter_value, log.utm_medium)
                                                   END
                                           when 'utm_campaign' then
                                               case  content.second_parameter_judgment
                                                   when '完全一致' then checkAllMatch(content.second_parameter_value, log.utm_campaign)
                                                   when '前方一致' then checkStartMatch(content.second_parameter_value, log.utm_campaign)
                                                   when '中間一致' then checkMiddleMatch(content.second_parameter_value, log.utm_campaign)
                                                   when '後方一致' then checkSuffixMatch(content.second_parameter_value, log.utm_campaign)
                                                   END
                                           when '会員種別' then
                                               case  content.second_parameter_judgment
                                                   when '完全一致' then checkAllMatch(content.second_parameter_value, log.membership_type)
                                                   when '前方一致' then checkStartMatch(content.second_parameter_value, log.membership_type)
                                                   when '中間一致' then checkMiddleMatch(content.second_parameter_value, log.membership_type)
                                                   when '後方一致' then checkSuffixMatch(content.second_parameter_value, log.membership_type)
                                                   END
                                           when 'サイト内遷移情報' then
                                               case  content.second_parameter_judgment
                                                   when '完全一致' then checkAllMatch(content.second_parameter_value, log.site_transition_information)
                                                   when '前方一致' then checkStartMatch(content.second_parameter_value, log.site_transition_information)
                                                   when '中間一致' then checkMiddleMatch(content.second_parameter_value, log.site_transition_information)
                                                   when '後方一致' then checkSuffixMatch(content.second_parameter_value, log.site_transition_information)
                                                   END
                                           when 'ページ' then
                                               case  content.second_parameter_judgment
                                                   when '完全一致' then checkAllMatch(content.second_parameter_value, log.page)
                                                   when '前方一致' then checkStartMatch(content.second_parameter_value, log.page)
                                                   when '中間一致' then checkMiddleMatch(content.second_parameter_value, log.page)
                                                   when '後方一致' then checkSuffixMatch(content.second_parameter_value, log.page)
                                                   END
                                           when '領域' then
                                               case  content.second_parameter_judgment
                                                   when '完全一致' then checkAllMatch(content.second_parameter_value, log.medical_domain)
                                                   when '前方一致' then checkStartMatch(content.second_parameter_value, log.medical_domain)
                                                   when '中間一致' then checkMiddleMatch(content.second_parameter_value, log.medical_domain)
                                                   when '後方一致' then checkSuffixMatch(content.second_parameter_value, log.medical_domain)
                                                   END
                                           when '製品情報' then
                                               case  content.second_parameter_judgment
                                                   when '完全一致' then checkAllMatch(content.second_parameter_value, log.product_info)
                                                   when '前方一致' then checkStartMatch(content.second_parameter_value, log.product_info)
                                                   when '中間一致' then checkMiddleMatch(content.second_parameter_value, log.product_info)
                                                   when '後方一致' then checkSuffixMatch(content.second_parameter_value, log.product_info)
                                                   END
                                           when '会員限定区分' then
                                               case  content.second_parameter_judgment
                                                   when '完全一致' then checkAllMatch(content.second_parameter_value, log.member_category)
                                                   when '前方一致' then checkStartMatch(content.second_parameter_value, log.member_category)
                                                   when '中間一致' then checkMiddleMatch(content.second_parameter_value, log.member_category)
                                                   when '後方一致' then checkSuffixMatch(content.second_parameter_value, log.member_category)
                                                   END
                                           when 'リンク情報' then
                                               case  content.second_parameter_judgment
                                                   when '完全一致' then checkAllMatch(content.second_parameter_value, log.link_information)
                                                   when '前方一致' then checkStartMatch(content.second_parameter_value, log.link_information)
                                                   when '中間一致' then checkMiddleMatch(content.second_parameter_value, log.link_information)
                                                   when '後方一致' then checkSuffixMatch(content.second_parameter_value, log.link_information)
                                                   END
                                           when '遷移先ページ' then
                                               case  content.second_parameter_judgment
                                                   when '完全一致' then checkAllMatch(content.second_parameter_value, log.destination_page)
                                                   when '前方一致' then checkStartMatch(content.second_parameter_value, log.destination_page)
                                                   when '中間一致' then checkMiddleMatch(content.second_parameter_value, log.destination_page)
                                                   when '後方一致' then checkSuffixMatch(content.second_parameter_value, log.destination_page)
                                                   END
                                           when '動画ID' then
                                               case  content.second_parameter_judgment
                                                   when '完全一致' then checkAllMatch(content.second_parameter_value, log.video_id)
                                                   when '前方一致' then checkStartMatch(content.second_parameter_value, log.video_id)
                                                   when '中間一致' then checkMiddleMatch(content.second_parameter_value, log.video_id)
                                                   when '後方一致' then checkSuffixMatch(content.second_parameter_value, log.video_id)
                                                   END
                                           when '検索条件' then
                                               case  content.second_parameter_judgment
                                                   when '完全一致' then checkAllMatch(content.second_parameter_value, log.search_condition)
                                                   when '前方一致' then checkStartMatch(content.second_parameter_value, log.search_condition)
                                                   when '中間一致' then checkMiddleMatch(content.second_parameter_value, log.search_condition)
                                                   when '後方一致' then checkSuffixMatch(content.second_parameter_value, log.search_condition)
                                                   END
                                           when 'セミナー情報' then
                                               case  content.second_parameter_judgment
                                                   when '完全一致' then checkAllMatch(content.second_parameter_value, log.seminar_info)
                                                   when '前方一致' then checkStartMatch(content.second_parameter_value, log.seminar_info)
                                                   when '中間一致' then checkMiddleMatch(content.second_parameter_value, log.seminar_info)
                                                   when '後方一致' then checkSuffixMatch(content.second_parameter_value, log.seminar_info)
                                                   END
                                           when 'セミナー日時' then
                                               case  content.second_parameter_judgment
                                                   when '完全一致' then checkAllMatch(content.second_parameter_value, log.seminar_date_time)
                                                   when '前方一致' then checkStartMatch(content.second_parameter_value, log.seminar_date_time)
                                                   when '中間一致' then checkMiddleMatch(content.second_parameter_value, log.seminar_date_time)
                                                   when '後方一致' then checkSuffixMatch(content.second_parameter_value, log.seminar_date_time)
                                                   END
                                           when '入室時間' then
                                               case  content.second_parameter_judgment
                                                   when '完全一致' then checkAllMatch(content.second_parameter_value, log.entry_time)
                                                   when '前方一致' then checkStartMatch(content.second_parameter_value, log.entry_time)
                                                   when '中間一致' then checkMiddleMatch(content.second_parameter_value, log.entry_time)
                                                   when '後方一致' then checkSuffixMatch(content.second_parameter_value, log.entry_time)
                                                   END
                                           when '退出時間' then
                                               case  content.second_parameter_judgment
                                                   when '完全一致' then checkAllMatch(content.second_parameter_value, log.leaving_time)
                                                   when '前方一致' then checkStartMatch(content.second_parameter_value, log.leaving_time)
                                                   when '中間一致' then checkMiddleMatch(content.second_parameter_value, log.leaving_time)
                                                   when '後方一致' then checkSuffixMatch(content.second_parameter_value, log.leaving_time)
                                                   END
                                           when '合計滞在時間' then
                                               case  content.second_parameter_judgment
                                                   when '完全一致' then checkAllMatch(content.second_parameter_value, log.stay_time)
                                                   when '前方一致' then checkStartMatch(content.second_parameter_value, log.stay_time)
                                                   when '中間一致' then checkMiddleMatch(content.second_parameter_value, log.stay_time)
                                                   when '後方一致' then checkSuffixMatch(content.second_parameter_value, log.stay_time)
                                                   END
                                           when 'プレイヤーID' then
                                               case  content.second_parameter_judgment
                                                   when '完全一致' then checkAllMatch(content.second_parameter_value, log.player_id)
                                                   when '前方一致' then checkStartMatch(content.second_parameter_value, log.player_id)
                                                   when '中間一致' then checkMiddleMatch(content.second_parameter_value, log.player_id)
                                                   when '後方一致' then checkSuffixMatch(content.second_parameter_value, log.player_id)
                                                   END
                                           when '取り込みログリファラー' then
                                               case  content.second_parameter_judgment
                                                   when '完全一致' then checkAllMatch(content.second_parameter_value, log.ingestion_log_ref)
                                                   when '前方一致' then checkStartMatch(content.second_parameter_value, log.ingestion_log_ref)
                                                   when '中間一致' then checkMiddleMatch(content.second_parameter_value, log.ingestion_log_ref)
                                                   when '後方一致' then checkSuffixMatch(content.second_parameter_value, log.ingestion_log_ref)
                                                   END
                                           when '取り込みログUA' then
                                               case  content.second_parameter_judgment
                                                   when '完全一致' then checkAllMatch(content.second_parameter_value, log.import_log_ua)
                                                   when '前方一致' then checkStartMatch(content.second_parameter_value, log.import_log_ua)
                                                   when '中間一致' then checkMiddleMatch(content.second_parameter_value, log.import_log_ua)
                                                   when '後方一致' then checkSuffixMatch(content.second_parameter_value, log.import_log_ua)
                                                   END
                                   end
                           when content.url is not null and content.second_parameter_name is null
                               then log.page_url = content.url
                           when content.url is null and content.second_parameter_value is not null
                               then
                               case content.second_parameter_name
                                   when 'セミナーID' then
                                       case  content.second_parameter_judgment
                                           when '完全一致' then checkAllMatch(content.second_parameter_value, log.seminar_id)
                                           when '前方一致' then checkStartMatch(content.second_parameter_value, log.seminar_id)
                                           when '中間一致' then checkMiddleMatch(content.second_parameter_value, log.seminar_id)
                                           when '後方一致' then checkSuffixMatch(content.second_parameter_value, log.seminar_id)
                                           END
                                   when 'xmail' then
                                       case  content.second_parameter_judgment
                                           when '完全一致' then checkAllMatch(content.second_parameter_value, log.x_mail)
                                           when '前方一致' then checkStartMatch(content.second_parameter_value, log.x_mail)
                                           when '中間一致' then checkMiddleMatch(content.second_parameter_value, log.x_mail)
                                           when '後方一致' then checkSuffixMatch(content.second_parameter_value, log.x_mail)
                                           END
                                   when 'セグメントID' then
                                       case  content.second_parameter_judgment
                                           when '完全一致' then checkAllMatch(content.second_parameter_value, log.segment_id)
                                           when '前方一致' then checkStartMatch(content.second_parameter_value, log.segment_id)
                                           when '中間一致' then checkMiddleMatch(content.second_parameter_value, log.segment_id)
                                           when '後方一致' then checkSuffixMatch(content.second_parameter_value, log.segment_id)
                                           END
                                   when '動画情報' then
                                       case  content.second_parameter_judgment
                                           when '完全一致' then checkAllMatch(content.second_parameter_value, log.video_information)
                                           when '前方一致' then checkStartMatch(content.second_parameter_value, log.video_information)
                                           when '中間一致' then checkMiddleMatch(content.second_parameter_value, log.video_information)
                                           when '後方一致' then checkSuffixMatch(content.second_parameter_value, log.video_information)
                                           END
                                   when 'ファイル名' then
                                       case  content.second_parameter_judgment
                                           when '完全一致' then checkAllMatch(content.second_parameter_value, log.file_name)
                                           when '前方一致' then checkStartMatch(content.second_parameter_value, log.file_name)
                                           when '中間一致' then checkMiddleMatch(content.second_parameter_value, log.file_name)
                                           when '後方一致' then checkSuffixMatch(content.second_parameter_value, log.file_name)
                                           END
                                   when '文書情報' then
                                       case  content.second_parameter_judgment
                                           when '完全一致' then checkAllMatch(content.second_parameter_value, log.document_information)
                                           when '前方一致' then checkStartMatch(content.second_parameter_value, log.document_information)
                                           when '中間一致' then checkMiddleMatch(content.second_parameter_value, log.document_information)
                                           when '後方一致' then checkSuffixMatch(content.second_parameter_value, log.document_information)
                                           END
                                   when 'ログ受付日時' then
                                       case  content.second_parameter_judgment
                                           when '完全一致' then checkAllMatch(content.second_parameter_value, log.date_time)
                                           when '前方一致' then checkStartMatch(content.second_parameter_value, log.date_time)
                                           when '中間一致' then checkMiddleMatch(content.second_parameter_value, log.date_time)
                                           when '後方一致' then checkSuffixMatch(content.second_parameter_value, log.date_time)
                                           END
                                   when 'リモートIPアドレス' then
                                       case  content.second_parameter_judgment
                                           when '完全一致' then checkAllMatch(content.second_parameter_value, log.ip_address)
                                           when '前方一致' then checkStartMatch(content.second_parameter_value, log.ip_address)
                                           when '中間一致' then checkMiddleMatch(content.second_parameter_value, log.ip_address)
                                           when '後方一致' then checkSuffixMatch(content.second_parameter_value, log.ip_address)
                                           END
                                   when 'モード' then
                                       case  content.second_parameter_judgment
                                           when '完全一致' then checkAllMatch(content.second_parameter_value, log.mode)
                                           when '前方一致' then checkStartMatch(content.second_parameter_value, log.mode)
                                           when '中間一致' then checkMiddleMatch(content.second_parameter_value, log.mode)
                                           when '後方一致' then checkSuffixMatch(content.second_parameter_value, log.mode)
                                           END
                                   when 'デバイス' then
                                       case  content.second_parameter_judgment
                                           when '完全一致' then checkAllMatch(content.second_parameter_value, log.device)
                                           when '前方一致' then checkStartMatch(content.second_parameter_value, log.device)
                                           when '中間一致' then checkMiddleMatch(content.second_parameter_value, log.device)
                                           when '後方一致' then checkSuffixMatch(content.second_parameter_value, log.device)
                                           END
                                   when 'セッションID' then
                                       case  content.second_parameter_judgment
                                           when '完全一致' then checkAllMatch(content.second_parameter_value, log.session_id)
                                           when '前方一致' then checkStartMatch(content.second_parameter_value, log.session_id)
                                           when '中間一致' then checkMiddleMatch(content.second_parameter_value, log.session_id)
                                           when '後方一致' then checkSuffixMatch(content.second_parameter_value, log.session_id)
                                           END
                                   when 'セッション状況' then
                                       case  content.second_parameter_judgment
                                           when '完全一致' then checkAllMatch(content.second_parameter_value, log.session_status)
                                           when '前方一致' then checkStartMatch(content.second_parameter_value, log.session_status)
                                           when '中間一致' then checkMiddleMatch(content.second_parameter_value, log.session_status)
                                           when '後方一致' then checkSuffixMatch(content.second_parameter_value, log.session_status)
                                           END
                                   when '顧客ID' then
                                       case  content.second_parameter_judgment
                                           when '完全一致' then checkAllMatch(content.second_parameter_value, log.customer_id)
                                           when '前方一致' then checkStartMatch(content.second_parameter_value, log.customer_id)
                                           when '中間一致' then checkMiddleMatch(content.second_parameter_value, log.customer_id)
                                           when '後方一致' then checkSuffixMatch(content.second_parameter_value, log.customer_id)
                                           END
                                   when 'ランディングフラグ' then
                                       case  content.second_parameter_judgment
                                           when '完全一致' then checkAllMatch(content.second_parameter_value, log.landing_flag)
                                           when '前方一致' then checkStartMatch(content.second_parameter_value, log.landing_flag)
                                           when '中間一致' then checkMiddleMatch(content.second_parameter_value, log.landing_flag)
                                           when '後方一致' then checkSuffixMatch(content.second_parameter_value, log.landing_flag)
                                           END
                                   when 'URL/ダウンロードファイル' then
                                       case  content.second_parameter_judgment
                                           when '完全一致' then checkAllMatch(content.second_parameter_value, log.page_url)
                                           when '前方一致' then checkStartMatch(content.second_parameter_value, log.page_url)
                                           when '中間一致' then checkMiddleMatch(content.second_parameter_value, log.page_url)
                                           when '後方一致' then checkSuffixMatch(content.second_parameter_value, log.page_url)
                                           END
                                   when 'ページ名' then
                                       case  content.second_parameter_judgment
                                           when '完全一致' then checkAllMatch(content.second_parameter_value, log.page_name)
                                           when '前方一致' then checkStartMatch(content.second_parameter_value, log.page_name)
                                           when '中間一致' then checkMiddleMatch(content.second_parameter_value, log.page_name)
                                           when '後方一致' then checkSuffixMatch(content.second_parameter_value, log.page_name)
                                           END
                                   when 'アクション' then
                                       case  content.second_parameter_judgment
                                           when '完全一致' then checkAllMatch(content.second_parameter_value, log.action)
                                           when '前方一致' then checkStartMatch(content.second_parameter_value, log.action)
                                           when '中間一致' then checkMiddleMatch(content.second_parameter_value, log.action)
                                           when '後方一致' then checkSuffixMatch(content.second_parameter_value, log.action)
                                           END
                                   when '外部サイトリファラー' then
                                       case  content.second_parameter_judgment
                                           when '完全一致' then checkAllMatch(content.second_parameter_value, log.external_site_referrer)
                                           when '前方一致' then checkStartMatch(content.second_parameter_value, log.external_site_referrer)
                                           when '中間一致' then checkMiddleMatch(content.second_parameter_value, log.external_site_referrer)
                                           when '後方一致' then checkSuffixMatch(content.second_parameter_value, log.external_site_referrer)
                                           END
                                   when '参照元ドメイン名' then
                                       case  content.second_parameter_judgment
                                           when '完全一致' then checkAllMatch(content.second_parameter_value, log.referring_domain_name)
                                           when '前方一致' then checkStartMatch(content.second_parameter_value, log.referring_domain_name)
                                           when '中間一致' then checkMiddleMatch(content.second_parameter_value, log.referring_domain_name)
                                           when '後方一致' then checkSuffixMatch(content.second_parameter_value, log.referring_domain_name)
                                           END
                                   when '流入元種別' then
                                       case  content.second_parameter_judgment
                                           when '完全一致' then checkAllMatch(content.second_parameter_value, log.inflow_source_type)
                                           when '前方一致' then checkStartMatch(content.second_parameter_value, log.inflow_source_type)
                                           when '中間一致' then checkMiddleMatch(content.second_parameter_value, log.inflow_source_type)
                                           when '後方一致' then checkSuffixMatch(content.second_parameter_value, log.inflow_source_type)
                                           END
                                   when '検索エンジン名' then
                                       case  content.second_parameter_judgment
                                           when '完全一致' then checkAllMatch(content.second_parameter_value, log.search_engine_name)
                                           when '前方一致' then checkStartMatch(content.second_parameter_value, log.search_engine_name)
                                           when '中間一致' then checkMiddleMatch(content.second_parameter_value, log.search_engine_name)
                                           when '後方一致' then checkSuffixMatch(content.second_parameter_value, log.search_engine_name)
                                           END
                                   when '参照元サイト種別' then
                                       case  content.second_parameter_judgment
                                           when '完全一致' then checkAllMatch(content.second_parameter_value, log.referrer_site_type)
                                           when '前方一致' then checkStartMatch(content.second_parameter_value, log.referrer_site_type)
                                           when '中間一致' then checkMiddleMatch(content.second_parameter_value, log.referrer_site_type)
                                           when '後方一致' then checkSuffixMatch(content.second_parameter_value, log.referrer_site_type)
                                           END
                                   when '参照元サイト名' then
                                       case  content.second_parameter_judgment
                                           when '完全一致' then checkAllMatch(content.second_parameter_value, log.referrer_site_name)
                                           when '前方一致' then checkStartMatch(content.second_parameter_value, log.referrer_site_name)
                                           when '中間一致' then checkMiddleMatch(content.second_parameter_value, log.referrer_site_name)
                                           when '後方一致' then checkSuffixMatch(content.second_parameter_value, log.referrer_site_name)
                                           END
                                   when 'サイト内リファラー' then
                                       case  content.second_parameter_judgment
                                           when '完全一致' then checkAllMatch(content.second_parameter_value, log.in_site_referrer)
                                           when '前方一致' then checkStartMatch(content.second_parameter_value, log.in_site_referrer)
                                           when '中間一致' then checkMiddleMatch(content.second_parameter_value, log.in_site_referrer)
                                           when '後方一致' then checkSuffixMatch(content.second_parameter_value, log.in_site_referrer)
                                           END
                                   when 'User-Agent' then
                                       case  content.second_parameter_judgment
                                           when '完全一致' then checkAllMatch(content.second_parameter_value, log.user_agent)
                                           when '前方一致' then checkStartMatch(content.second_parameter_value, log.user_agent)
                                           when '中間一致' then checkMiddleMatch(content.second_parameter_value, log.user_agent)
                                           when '後方一致' then checkSuffixMatch(content.second_parameter_value, log.user_agent)
                                           END
                                   when 'OS' then
                                       case  content.second_parameter_judgment
                                           when '完全一致' then checkAllMatch(content.second_parameter_value, log.os_name)
                                           when '前方一致' then checkStartMatch(content.second_parameter_value, log.os_name)
                                           when '中間一致' then checkMiddleMatch(content.second_parameter_value, log.os_name)
                                           when '後方一致' then checkSuffixMatch(content.second_parameter_value, log.os_name)
                                           END
                                   when 'ブラウザ' then
                                       case  content.second_parameter_judgment
                                           when '完全一致' then checkAllMatch(content.second_parameter_value, log.browser)
                                           when '前方一致' then checkStartMatch(content.second_parameter_value, log.browser)
                                           when '中間一致' then checkMiddleMatch(content.second_parameter_value, log.browser)
                                           when '後方一致' then checkSuffixMatch(content.second_parameter_value, log.browser)
                                           END
                                   when '端末名' then
                                       case  content.second_parameter_judgment
                                           when '完全一致' then checkAllMatch(content.second_parameter_value, log.terminal_name)
                                           when '前方一致' then checkStartMatch(content.second_parameter_value, log.terminal_name)
                                           when '中間一致' then checkMiddleMatch(content.second_parameter_value, log.terminal_name)
                                           when '後方一致' then checkSuffixMatch(content.second_parameter_value, log.terminal_name)
                                           END
                                   when 'メーカー名' then
                                       case  content.second_parameter_judgment
                                           when '完全一致' then checkAllMatch(content.second_parameter_value, log.manufacture_name)
                                           when '前方一致' then checkStartMatch(content.second_parameter_value, log.manufacture_name)
                                           when '中間一致' then checkMiddleMatch(content.second_parameter_value, log.manufacture_name)
                                           when '後方一致' then checkSuffixMatch(content.second_parameter_value, log.manufacture_name)
                                           END
                                   when '国コード' then
                                       case  content.second_parameter_judgment
                                           when '完全一致' then checkAllMatch(content.second_parameter_value, log.country_code)
                                           when '前方一致' then checkStartMatch(content.second_parameter_value, log.country_code)
                                           when '中間一致' then checkMiddleMatch(content.second_parameter_value, log.country_code)
                                           when '後方一致' then checkSuffixMatch(content.second_parameter_value, log.country_code)
                                           END
                                   when '地域名' then
                                       case  content.second_parameter_judgment
                                           when '完全一致' then checkAllMatch(content.second_parameter_value, log.region_name)
                                           when '前方一致' then checkStartMatch(content.second_parameter_value, log.region_name)
                                           when '中間一致' then checkMiddleMatch(content.second_parameter_value, log.region_name)
                                           when '後方一致' then checkSuffixMatch(content.second_parameter_value, log.region_name)
                                           END
                                   when '都市名' then
                                       case  content.second_parameter_judgment
                                           when '完全一致' then checkAllMatch(content.second_parameter_value, log.name_of_city)
                                           when '前方一致' then checkStartMatch(content.second_parameter_value, log.name_of_city)
                                           when '中間一致' then checkMiddleMatch(content.second_parameter_value, log.name_of_city)
                                           when '後方一致' then checkSuffixMatch(content.second_parameter_value, log.name_of_city)
                                           END
                                   when 'リンク識別子' then
                                       case  content.second_parameter_judgment
                                           when '完全一致' then checkAllMatch(content.second_parameter_value, log.link_identifier)
                                           when '前方一致' then checkStartMatch(content.second_parameter_value, log.link_identifier)
                                           when '中間一致' then checkMiddleMatch(content.second_parameter_value, log.link_identifier)
                                           when '後方一致' then checkSuffixMatch(content.second_parameter_value, log.link_identifier)
                                           END
                                   when 'utm_source' then
                                       case  content.second_parameter_judgment
                                           when '完全一致' then checkAllMatch(content.second_parameter_value, log.utm_source)
                                           when '前方一致' then checkStartMatch(content.second_parameter_value, log.utm_source)
                                           when '中間一致' then checkMiddleMatch(content.second_parameter_value, log.utm_source)
                                           when '後方一致' then checkSuffixMatch(content.second_parameter_value, log.utm_source)
                                           END
                                   when 'utm_medium' then
                                       case  content.second_parameter_judgment
                                           when '完全一致' then checkAllMatch(content.second_parameter_value, log.utm_medium)
                                           when '前方一致' then checkStartMatch(content.second_parameter_value, log.utm_medium)
                                           when '中間一致' then checkMiddleMatch(content.second_parameter_value, log.utm_medium)
                                           when '後方一致' then checkSuffixMatch(content.second_parameter_value, log.utm_medium)
                                           END
                                   when 'utm_campaign' then
                                       case  content.second_parameter_judgment
                                           when '完全一致' then checkAllMatch(content.second_parameter_value, log.utm_campaign)
                                           when '前方一致' then checkStartMatch(content.second_parameter_value, log.utm_campaign)
                                           when '中間一致' then checkMiddleMatch(content.second_parameter_value, log.utm_campaign)
                                           when '後方一致' then checkSuffixMatch(content.second_parameter_value, log.utm_campaign)
                                           END
                                   when '会員種別' then
                                       case  content.second_parameter_judgment
                                           when '完全一致' then checkAllMatch(content.second_parameter_value, log.membership_type)
                                           when '前方一致' then checkStartMatch(content.second_parameter_value, log.membership_type)
                                           when '中間一致' then checkMiddleMatch(content.second_parameter_value, log.membership_type)
                                           when '後方一致' then checkSuffixMatch(content.second_parameter_value, log.membership_type)
                                           END
                                   when 'サイト内遷移情報' then
                                       case  content.second_parameter_judgment
                                           when '完全一致' then checkAllMatch(content.second_parameter_value, log.site_transition_information)
                                           when '前方一致' then checkStartMatch(content.second_parameter_value, log.site_transition_information)
                                           when '中間一致' then checkMiddleMatch(content.second_parameter_value, log.site_transition_information)
                                           when '後方一致' then checkSuffixMatch(content.second_parameter_value, log.site_transition_information)
                                           END
                                   when 'ページ' then
                                       case  content.second_parameter_judgment
                                           when '完全一致' then checkAllMatch(content.second_parameter_value, log.page)
                                           when '前方一致' then checkStartMatch(content.second_parameter_value, log.page)
                                           when '中間一致' then checkMiddleMatch(content.second_parameter_value, log.page)
                                           when '後方一致' then checkSuffixMatch(content.second_parameter_value, log.page)
                                           END
                                   when '領域' then
                                       case  content.second_parameter_judgment
                                           when '完全一致' then checkAllMatch(content.second_parameter_value, log.medical_domain)
                                           when '前方一致' then checkStartMatch(content.second_parameter_value, log.medical_domain)
                                           when '中間一致' then checkMiddleMatch(content.second_parameter_value, log.medical_domain)
                                           when '後方一致' then checkSuffixMatch(content.second_parameter_value, log.medical_domain)
                                           END
                                   when '製品情報' then
                                       case  content.second_parameter_judgment
                                           when '完全一致' then checkAllMatch(content.second_parameter_value, log.product_info)
                                           when '前方一致' then checkStartMatch(content.second_parameter_value, log.product_info)
                                           when '中間一致' then checkMiddleMatch(content.second_parameter_value, log.product_info)
                                           when '後方一致' then checkSuffixMatch(content.second_parameter_value, log.product_info)
                                           END
                                   when '会員限定区分' then
                                       case  content.second_parameter_judgment
                                           when '完全一致' then checkAllMatch(content.second_parameter_value, log.member_category)
                                           when '前方一致' then checkStartMatch(content.second_parameter_value, log.member_category)
                                           when '中間一致' then checkMiddleMatch(content.second_parameter_value, log.member_category)
                                           when '後方一致' then checkSuffixMatch(content.second_parameter_value, log.member_category)
                                           END
                                   when 'リンク情報' then
                                       case  content.second_parameter_judgment
                                           when '完全一致' then checkAllMatch(content.second_parameter_value, log.link_information)
                                           when '前方一致' then checkStartMatch(content.second_parameter_value, log.link_information)
                                           when '中間一致' then checkMiddleMatch(content.second_parameter_value, log.link_information)
                                           when '後方一致' then checkSuffixMatch(content.second_parameter_value, log.link_information)
                                           END
                                   when '遷移先ページ' then
                                       case  content.second_parameter_judgment
                                           when '完全一致' then checkAllMatch(content.second_parameter_value, log.destination_page)
                                           when '前方一致' then checkStartMatch(content.second_parameter_value, log.destination_page)
                                           when '中間一致' then checkMiddleMatch(content.second_parameter_value, log.destination_page)
                                           when '後方一致' then checkSuffixMatch(content.second_parameter_value, log.destination_page)
                                           END
                                   when '動画ID' then
                                       case  content.second_parameter_judgment
                                           when '完全一致' then checkAllMatch(content.second_parameter_value, log.video_id)
                                           when '前方一致' then checkStartMatch(content.second_parameter_value, log.video_id)
                                           when '中間一致' then checkMiddleMatch(content.second_parameter_value, log.video_id)
                                           when '後方一致' then checkSuffixMatch(content.second_parameter_value, log.video_id)
                                           END
                                   when '検索条件' then
                                       case  content.second_parameter_judgment
                                           when '完全一致' then checkAllMatch(content.second_parameter_value, log.search_condition)
                                           when '前方一致' then checkStartMatch(content.second_parameter_value, log.search_condition)
                                           when '中間一致' then checkMiddleMatch(content.second_parameter_value, log.search_condition)
                                           when '後方一致' then checkSuffixMatch(content.second_parameter_value, log.search_condition)
                                           END
                                   when 'セミナー情報' then
                                       case  content.second_parameter_judgment
                                           when '完全一致' then checkAllMatch(content.second_parameter_value, log.seminar_info)
                                           when '前方一致' then checkStartMatch(content.second_parameter_value, log.seminar_info)
                                           when '中間一致' then checkMiddleMatch(content.second_parameter_value, log.seminar_info)
                                           when '後方一致' then checkSuffixMatch(content.second_parameter_value, log.seminar_info)
                                           END
                                   when 'セミナー日時' then
                                       case  content.second_parameter_judgment
                                           when '完全一致' then checkAllMatch(content.second_parameter_value, log.seminar_date_time)
                                           when '前方一致' then checkStartMatch(content.second_parameter_value, log.seminar_date_time)
                                           when '中間一致' then checkMiddleMatch(content.second_parameter_value, log.seminar_date_time)
                                           when '後方一致' then checkSuffixMatch(content.second_parameter_value, log.seminar_date_time)
                                           END
                                   when '入室時間' then
                                       case  content.second_parameter_judgment
                                           when '完全一致' then checkAllMatch(content.second_parameter_value, log.entry_time)
                                           when '前方一致' then checkStartMatch(content.second_parameter_value, log.entry_time)
                                           when '中間一致' then checkMiddleMatch(content.second_parameter_value, log.entry_time)
                                           when '後方一致' then checkSuffixMatch(content.second_parameter_value, log.entry_time)
                                           END
                                   when '退出時間' then
                                       case  content.second_parameter_judgment
                                           when '完全一致' then checkAllMatch(content.second_parameter_value, log.leaving_time)
                                           when '前方一致' then checkStartMatch(content.second_parameter_value, log.leaving_time)
                                           when '中間一致' then checkMiddleMatch(content.second_parameter_value, log.leaving_time)
                                           when '後方一致' then checkSuffixMatch(content.second_parameter_value, log.leaving_time)
                                           END
                                   when '合計滞在時間' then
                                       case  content.second_parameter_judgment
                                           when '完全一致' then checkAllMatch(content.second_parameter_value, log.stay_time)
                                           when '前方一致' then checkStartMatch(content.second_parameter_value, log.stay_time)
                                           when '中間一致' then checkMiddleMatch(content.second_parameter_value, log.stay_time)
                                           when '後方一致' then checkSuffixMatch(content.second_parameter_value, log.stay_time)
                                           END
                                   when 'プレイヤーID' then
                                       case  content.second_parameter_judgment
                                           when '完全一致' then checkAllMatch(content.second_parameter_value, log.player_id)
                                           when '前方一致' then checkStartMatch(content.second_parameter_value, log.player_id)
                                           when '中間一致' then checkMiddleMatch(content.second_parameter_value, log.player_id)
                                           when '後方一致' then checkSuffixMatch(content.second_parameter_value, log.player_id)
                                           END
                                   when '取り込みログリファラー' then
                                       case  content.second_parameter_judgment
                                           when '完全一致' then checkAllMatch(content.second_parameter_value, log.ingestion_log_ref)
                                           when '前方一致' then checkStartMatch(content.second_parameter_value, log.ingestion_log_ref)
                                           when '中間一致' then checkMiddleMatch(content.second_parameter_value, log.ingestion_log_ref)
                                           when '後方一致' then checkSuffixMatch(content.second_parameter_value, log.ingestion_log_ref)
                                           END
                                   when '取り込みログUA' then
                                       case  content.second_parameter_judgment
                                           when '完全一致' then checkAllMatch(content.second_parameter_value, log.import_log_ua)
                                           when '前方一致' then checkStartMatch(content.second_parameter_value, log.import_log_ua)
                                           when '中間一致' then checkMiddleMatch(content.second_parameter_value, log.import_log_ua)
                                           when '後方一致' then checkSuffixMatch(content.second_parameter_value, log.import_log_ua)
                                           END
                                   END
                           END
where log.date_time BETWEEN DATE_SUB(DATE_SUB(CURDATE(), INTERVAL 4 DAY), INTERVAL hcondition.viewing_judgment_period DAY) AND DATE_SUB(CURDATE(), INTERVAL 4 DAY)
  and hcondition.status is true and content.condition_id='urc_01_pt03'
group by content.condition_id, content.step_no
order by number_of_viewed_contents desc limit 1;

