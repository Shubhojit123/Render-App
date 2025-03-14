import { useState } from "react";
import axios from "axios";
import { Button } from "@/components/ui/button";
import { Card, CardContent } from "@/components/ui/card";
import { toast } from "react-hot-toast";

const CouponClaimCard = () => {
    const [loading, setLoading] = useState(false);
    const [couponCode, setCouponCode] = useState(null);

    const claimCoupon = async () => {
        setLoading(true);
        try {
            const response = await axios.get("http://localhost:8080/api/coupons/claim", { withCredentials: true });

            if (response.data.includes("🎉 Coupon Claimed:")) {
                const code = response.data.replace(response.data);
                setCouponCode(code);
                toast.success("Coupon claimed successfully!");
            } else {
                toast.error(response.data);
            }
        } catch (error) {
            toast.error("Something went wrong. Try again later.");
        }
        setLoading(false);
    };

    return (
        <div className="flex flex-col items-center gap-6 p-6 bg-white rounded-lg shadow-lg">
            <Button onClick={claimCoupon} disabled={loading} className="w-40">
                {loading ? "Claiming..." : "Claim Coupon"}
            </Button>

            {couponCode && (
                <Card className="w-80 bg-green-100 border border-green-400">
                    <CardContent className="p-6 text-center">
                        <h3 className="text-lg font-semibold text-green-800">🎉 Coupon Claimed!</h3>
                        <p className="text-xl font-bold text-blue-600">{couponCode}</p>
                    </CardContent>
                </Card>
            )}
        </div>
    );
};

export default CouponClaimCard;
