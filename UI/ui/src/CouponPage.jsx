import CouponClaimCard from "@/components/CouponClaimCard";
// import { Toaster } from "react-hot-toast";

export default function CouponPage() {
    return (
        <div className="flex justify-center items-center h-screen bg-gray-100">
            <CouponClaimCard />
            <Toaster position="top-right" />
        </div>
    );
}
