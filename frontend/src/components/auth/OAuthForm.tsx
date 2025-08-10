import { OauthProvider } from "@/types/auth";
import { FaGithub } from "react-icons/fa";
import { FcGoogle } from "react-icons/fc";

export default function OAuth() {
  const handleOAuthLogin = async (provider: OauthProvider) => {
    window.location.href = `${
      import.meta.env.VITE_SERVER_URL
    }/oauth2/authorization/${provider}`;
  };

  return (
    <div className="flex justify-center gap-6 mt-6">
      <button
        type="button"
        className="p-3 rounded-full bg-surface border border-secondaryText text-primaryText hover:bg-background transition"
        onClick={() => handleOAuthLogin("google")}
      >
        <FcGoogle size={28} />
      </button>

      <button
        type="button"
        className="p-3 rounded-full bg-surface border border-secondaryText text-primaryText hover:bg-background transition"
        onClick={() => handleOAuthLogin("github")}
      >
        <FaGithub size={28} />
      </button>
    </div>
  );
}
