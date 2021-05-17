//
//  RsVehicle.h
//  TestObjective-C
//
//  Created by Матвей Попов on 17.05.2021.
//

#import <Foundation/Foundation.h>
#import "RSPassenger.h"

NS_ASSUME_NONNULL_BEGIN

@interface RsVehicle : NSObject

@property (nonatomic, strong, readonly) RSPassenger* captain;
@property (nonatomic, assign, readonly) BOOL running;

- (instancetype)initWithPassenger:(RSPassenger*)captain;

- (void)toggleEngine:(BOOL)running;

@end

NS_ASSUME_NONNULL_END
