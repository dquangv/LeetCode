type Bank struct {
	accounts      []int64
	accountsCount int
}

func Constructor(balance []int64) Bank {
	return Bank{
		accounts:      balance,
		accountsCount: len(balance),
	}
}

func (this *Bank) Transfer(account1 int, account2 int, money int64) bool {
	if this.accountsCount < account1 || this.accountsCount < account2 {
		return false
	}

	if this.accounts[account1-1] < money {
		return false
	}

	this.accounts[account1-1] -= money
	this.accounts[account2-1] += money
	return true
}

func (this *Bank) Deposit(account int, money int64) bool {
	if this.accountsCount < account {
		return false
	}

	this.accounts[account-1] += money
	return true
}

func (this *Bank) Withdraw(account int, money int64) bool {
	if this.accountsCount < account {
		return false
	}

	if this.accounts[account-1] < money {
		return false
	}

	this.accounts[account-1] -= money
	return true
}
