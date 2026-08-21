package com.example.data.model

enum class SubscriptionTier(
    val title: String,
    val priceDisplay: String,
    val billingPeriod: String,
    val regularPriceDisplay: String? = null
) {
    FREE("Free Explorer", "$0", "Forever"),
    MONTHLY_PRO("Psyche+ Monthly", "$4.99", "per month", regularPriceDisplay = "$9.99"),
    ANNUAL_PRO("Psyche+ Celestial Annual", "$29.99", "per year ($2.49/mo)"),
    LIFETIME_FULL_ACCESS("One-Time Full Access", "$4.99", "one-time payment", regularPriceDisplay = "$9.99")
}

data class UserSubscription(
    val isPremium: Boolean = false,
    val tier: SubscriptionTier = SubscriptionTier.FREE,
    val gemsBalance: Int = 10, // 10 Celestial Gems = 1 full AI report credit
    val adsWatchedCount: Int = 0,
    val adFreeUntilMillis: Long = 0L,
    val hasClaimedReviewBonus: Boolean = false
) {
    val hasAdFreePass: Boolean
        get() = isPremium || (adFreeUntilMillis > System.currentTimeMillis())

    val isMonthlyOrYearly: Boolean
        get() = tier == SubscriptionTier.MONTHLY_PRO || tier == SubscriptionTier.ANNUAL_PRO
}

