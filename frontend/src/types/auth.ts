import { z } from "zod";

export const authTokensSchema = z.object({
  accessToken: z.string(),
  refreshToken: z.string(),
});

export const loginRequestSchema = z.object({
  email: z.string().email(),
  password: z.string(),
});

export const registerRequestSchema = z.object({
  name: z.string().email(),
  email: z.string().email(),
  password: z.string(),
});

export type AuthTokens = z.infer<typeof authTokensSchema>;
export type LoginRequest = z.infer<typeof loginRequestSchema>;
export type RegisterRequest = z.infer<typeof registerRequestSchema>;
