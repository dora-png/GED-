Nom : ELLA BELINGA Jean-Paul
Matricule : 21U2568
1. Les départements de la même région que Paris
	SELECT * FROM departement WHERE codeReg = (SELECT codeReg FROM departement WHERE nomDept='Paris'); 

2. Liste des régions (code et nom) et le nombre de départements de chacune
	SELECT codeReg, nomReg, (SELECT COUNT(*) FROM departement d WHERE d.codeReg=r.codeReg ) AS depNombre FROM region r;

3. Liste des départements dans lesquels il y a eu moins de 100 livraisons de vaccin (code département, nom et nombre de livraison)
SELECT DISTINCT stock_vaccin.codeDept, departement.nomDept, (SELECT COUNT(*) FROM stock_vaccin AS st WHERE st.codeDept=departement.codeDept) AS nbre FROM stock_vaccin INNER JOIN departement WHERE departement.codeDept=stock_vaccin.codeDept AND (SELECT COUNT(*) FROM stock_vaccin st WHERE st.codeDept=stock_vaccin.codeDept)<100;

4. Liste des départements dans lesquels il y a eu entre 100 et 200 livraisons
SELECT DISTINCT stock_vaccin.codeDept, departement.nomDept, (SELECT COUNT(*) FROM stock_vaccin AS st WHERE st.codeDept=departement.codeDept) AS nbre FROM stock_vaccin INNER JOIN departement WHERE departement.codeDept=stock_vaccin.codeDept AND (SELECT COUNT(*) FROM stock_vaccin st WHERE st.codeDept=stock_vaccin.codeDept) BETWEEN 100 and 200;

5. Liste des régions dans lesquelles il y a eu moins de 300 livraisons
SELECT DISTINCT stock_vaccin.codeDept, departement.nomDept, (SELECT COUNT(*) FROM stock_vaccin AS st WHERE st.codeDept=departement.codeDept) AS nbre FROM stock_vaccin INNER JOIN departement WHERE departement.codeDept=stock_vaccin.codeDept AND (SELECT COUNT(*) FROM stock_vaccin st WHERE st.codeDept=stock_vaccin.codeDept) <300;

6. La(les) région(s) qui a(ont) le plus de département
SELECT P.nom, MAX(nbre) from (SELECT DISTINCT region.nomReg as nom, (SELECT COUNT(*) FROM departement AS st WHERE st.codeReg=region.codeReg) AS nbre FROM region INNER JOIN departement) as P;

7. Le vaccin le plus utilisé
SELECT SUM(nbDose) AS Nombre_dose ,codeVaccin FROM stock_vaccin GROUP BY codeVaccin ORDER BY Nombre_dose ASC LIMIT 1;

8. Le vaccin le moins utilisé
SELECT SUM(nbDose) AS Nombre_dose ,codeVaccin FROM stock_vaccin GROUP BY codeVaccin ORDER BY Nombre_dose DESC LIMIT 1;

9. Le département qui a reçu le plus de lots de vaccins

10. La région qui a reçu le plus de lots de vaccins

11. La région qui a reçu le moins de lots de vaccins

12. Les 20 départements qui ont consommé le plus de vaccins

13. Le nombre de doses de vaccins livrés par jour

14. Le 10 jours où il y a eu le plus de livraisons de vaccins

