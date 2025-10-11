func maximumTotalDamage(power []int) int64 {
	sort.Ints(power)
	lastM2Damage, lastM1Damage := 0, 0
	curDamage, curValue := power[0], power[0]
	maxDamage := curDamage

	for i := 1; i < len(power); i++ {
		if power[i] == curValue {
			curDamage += power[i]
			if curDamage > maxDamage {
				maxDamage = curDamage
			}
			continue
		}

		switch power[i] - curValue {
		case 1:
			lastM2Damage, lastM1Damage, curDamage = max(lastM2Damage, lastM1Damage), max(lastM2Damage, lastM1Damage, curDamage), lastM2Damage
		case 2:
			lastM2Damage, lastM1Damage, curDamage = max(lastM2Damage, lastM1Damage, curDamage), max(lastM2Damage, lastM1Damage, curDamage), max(lastM1Damage, lastM2Damage)
		default:
			tempMax := max(lastM2Damage, lastM1Damage, curDamage)
			lastM1Damage, lastM2Damage, curDamage = tempMax, tempMax, tempMax
		}
		curValue = power[i]
		curDamage += power[i]
		if curDamage > maxDamage {
			maxDamage = curDamage
		}
	}

	return int64(maxDamage)
}