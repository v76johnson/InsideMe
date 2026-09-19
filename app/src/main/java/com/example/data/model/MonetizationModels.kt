package com.example.data.model

enum class SubscriptionTier(
    val title: String,
    val priceDisplay: String,
    val billingPeriod: String,
    val regularPriceDisplay: String? = null
) {
    FREE("Free Explorer", "$0", "Forever"),
    MONTHLY_PRO("Psyche+ Monthly", "$2.99", "for first month ($4.99/mo after)", regularPriceDisplay = "$4.99"),
    ANNUAL_PRO("Psyche+ Celestial Annual", "$29.99", "per year ($2.50/mo)"),
    LIFETIME_FULL_ACCESS("One-Time Full Access", "$4.99", "one-time payment", regularPriceDisplay = "$9.99")
}

data class UserSubscription(
    val isPremium: Boolean = false,
    val tier: SubscriptionTier = SubscriptionTier.FREE,
    val hasUnlockedSynastry: Boolean = false,
    val hasUnlockedSynthesis: Boolean = false,
    val adsWatchedCount: Int = 0,
    val adFreeUntilMillis: Long = 0L,
    val hasClaimedReviewBonus: Boolean = false
) {
    val hasAdFreePass: Boolean
        get() = isPremium || (adFreeUntilMillis > System.currentTimeMillis())

    val isMonthlyOrYearly: Boolean
        get() = tier == SubscriptionTier.MONTHLY_PRO || tier == SubscriptionTier.ANNUAL_PRO
}
