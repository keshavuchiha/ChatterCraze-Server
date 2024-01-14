package com.chattercraze.Server.services;

import com.chattercraze.Server.Models.User;
import com.chattercraze.Server.configs.ControllerException;
import com.chattercraze.Server.dao.UserRepository;
import io.jsonwebtoken.*;
import io.jsonwebtoken.security.SecureDigestAlgorithm;
import io.jsonwebtoken.security.SignatureAlgorithm;
import jakarta.transaction.Transactional;
import org.bouncycastle.jcajce.interfaces.EdDSAPrivateKey;
import org.bouncycastle.jcajce.interfaces.EdDSAPublicKey;
import org.bouncycastle.jcajce.provider.asymmetric.ec.KeyFactorySpi;
import org.bouncycastle.openssl.PEMParser;
import org.bouncycastle.util.io.pem.PemObject;
import org.bouncycastle.util.io.pem.PemReader;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.security.crypto.bcrypt.BCrypt;
import org.springframework.stereotype.Service;
//import io.jsonwebtoken.impl.security.StandardSecureDigestAlgorithms;

import java.io.IOException;
import java.io.Reader;
import java.io.StringReader;
import java.security.*;
import java.security.spec.InvalidKeySpecException;
import java.security.spec.PKCS8EncodedKeySpec;
import java.security.spec.X509EncodedKeySpec;
import java.util.Date;
import java.util.HashMap;
import java.util.HexFormat;
import java.util.Map;

@Service
public class UserService {
    @Autowired
    UserRepository userRepository;
    @Transactional
    public String login(String email,String password) throws ControllerException, NoSuchAlgorithmException, InvalidKeySpecException, IOException {
        Pageable pageable = PageRequest.of(0, 1);
        Page<User> userPage = userRepository.findByEmail(email,pageable);
        if(userPage.isEmpty() || userPage.getContent().isEmpty()){
            throw new ControllerException("User not Found", HttpStatus.NOT_FOUND);
        }
        User user = userPage.getContent().get(0);
        if(BCrypt.checkpw(password,user.getPassword())){
//            Jwts.builder().signWith()
            HexFormat hexFormat = HexFormat.of();
//            PEMParser
            String tokenPrivateKey = System.getenv("TOKEN_PRIVATE_KEY");
            byte[] privateKeyBytes = hexFormat.parseHex(tokenPrivateKey);
            String tokenPublicKey = System.getenv("TOKEN_PUBLIC_KEY");
            byte[] publicKeyBytes = hexFormat.parseHex(tokenPublicKey);
            Reader reader = new StringReader(tokenPrivateKey);
//            PKCS8EncodedKeySpec privateKeySpec = new PKCS8EncodedKeySpec(privateKeyBytes);
//            X509EncodedKeySpec publicKeySpec = new X509EncodedKeySpec(publicKeyBytes);
//            PublicKey publicKey = fact.generatePublic(publicKeySpec);
            KeyPairGenerator ed25519 = KeyPairGenerator.getInstance("ED25519");
            KeyPair keyPair = ed25519.generateKeyPair();
//            KeyFactory ed25519 = KeyFactory.getInstance("ED25519");
//            EdDSAPrivateKey privateKey = (EdDSAPrivateKey) ed25519.generatePrivate(new PKCS8EncodedKeySpec(privateKeyBytes));
//            EdDSAPublicKey publicKey = (EdDSAPublicKey) ed25519.generatePrivate(new PKCS8EncodedKeySpec(publicKeyBytes));
            PrivateKey privateKey = keyPair.getPrivate();
            PublicKey publicKey = keyPair.getPublic();
//            PublicKey publicKey = keyFactory.generatePublic(publicKeySpec);
            Map<String,Object> claims=new HashMap<>();
            Date expirationDate = new Date(System.currentTimeMillis() + 24 * 60 * 60 * 1000);
            claims.put("name",user.getUser_id().toString());
            claims.put("exp",expirationDate.getTime()/1000);
            SignatureAlgorithm edDSA = Jwts.SIG.EdDSA;
            String jwtToken=Jwts.builder()
                    .claims(claims)
                    .signWith(privateKey, edDSA)
                    .compact();
//            Jwts.parserBuilder().setSigningKey().build().parseClaimsJws(jwtToken);
            Claims c= Jwts.parser().verifyWith(publicKey).build().parseSignedClaims(jwtToken).getPayload();
            System.out.println(c);
            return jwtToken;

        } else{
            throw new ControllerException("Password is incorrect", HttpStatus.UNAUTHORIZED);
        }
    }
}
